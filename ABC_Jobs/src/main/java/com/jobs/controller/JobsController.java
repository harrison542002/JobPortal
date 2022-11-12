package com.jobs.controller;

import com.jobs.model.*;
import com.jobs.services.JobService;
import com.jobs.services.ProfileService;
import com.jobs.services.RegisterService;
import com.jobs.services.UserProfileService;
import com.jobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class JobsController {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private JobService jobService;

	private ProfileCount profileCount = new ProfileCount();

	private JobCount jobCount = new JobCount();

	// Set new model attribute for user
	@ModelAttribute("user")
	public UserDto populateUser() {
		UserDto user = new UserDto();
		return user;
	}

	// Set new profile model attribute
	@ModelAttribute("profile")
	public UserProfileDto setProfile() {
		UserProfileDto profile = new UserProfileDto();
		return profile;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Model model, HttpServletRequest request) {

		String email = "";
		String password = "";
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
			}
		}
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		return "home";
	}

	/*
	 * Mapping for registration
	 */
	@GetMapping(value = "/registration")
	public String registration(Model model, HttpServletRequest request) {

		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@PostMapping(value = "/nextStep")
	public String confirmation(
			@RequestParam(value = "mode", required = false, defaultValue = "registration") String mode,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "ps", required = false) String password, @RequestParam(value = "code") String code,
			HttpServletRequest request, Model model) {

		String token = "";
		if (request.getCookies() != null) { // Acquire token from cookie
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("token")) {
					token = cookie.getValue();
					break;
				}
			}
		}

		if (mode.equals("login")) { // if the verification is for reset password, return reset password page
			if (token.equals(code)) {
				model.addAttribute("email", email);
				return "resetPwd";
			}
		}

		if (token.equals(code)) { // check if the verification token is matched
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user = registerService.save(user);
			// else display thank you page for registered user
			model.addAttribute("id", user.getUser_id());
			return "thankyou";
		}

		model.addAttribute("error", "true");
		return "confirmation";
	}

	@GetMapping(value = "/forgetPassword")
	public String forGetPassword(Model model) {

		return "forgetPassword";
	}

	@PostMapping(value = "/resetSuccess")
	public String reset(HttpServletRequest request, @RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email, Model model) {

		// Update user password
		int effectedRows = registerService.updatePassword(password, email);
		if (effectedRows > 0) {
			return "resetSuccessful";
		}
		return "resetPwd";
	}

	@GetMapping(value = "/setUpProfile")
	public String setUpProfile(Model model, @RequestParam(value = "id") Integer id) {
		model.addAttribute("id", id);
		return "setUpProfile";
	}

	// Handler for verification page
	@PostMapping(value = "/verification")
	public String verify(@ModelAttribute("user") UserDto userdto,
			@RequestParam(value = "mode", required = false, defaultValue = "registration") String mode,
			@RequestParam(value = "email", required = false) String email, Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException {

		if (mode.equals("login")) { // if the mode is in forget password, perform this procedure

			// Generate Verification Code
			String token = generateCode();
			// Send email message to user to verify email
			MimeMessage mimeMailMessage = sendEmailCode(email, token);
			javaMailSender.send(mimeMailMessage);
			Cookie tokenCookie = new Cookie("token", token);
			tokenCookie.setMaxAge(3600);
			response.addCookie(tokenCookie);
			model.addAttribute("mode", mode);
			model.addAttribute("email", email);

		} else { // if it is for registration

			if (!registerService.findUser(email).isEmpty()) {
				// if there is an account already registered
				// show error message and redirect to registration form to reconsider
				model.addAttribute("hasAccount", "true");
				System.out.println("true");
				return "registration";
			}

			// extract user object from userdto
			User user = userService.register(userdto);

			// Generate Verification Code
			String token = generateCode();

			// Send verification email
			MimeMessage mimeMailMessage = sendEmailCode(email, token);
			javaMailSender.send(mimeMailMessage);

			// Set cookie for token
			Cookie tokenCookie = new Cookie("token", token);
			tokenCookie.setMaxAge(3600);
			response.addCookie(tokenCookie);
			model.addAttribute("mode", mode);
			model.addAttribute("user", user);
		}
		return "confirmation";
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}

	@PostMapping(value = "/profile")
	public String profilePage(@ModelAttribute("profile") UserProfileDto profiledto,
			@RequestParam("file") MultipartFile file, @RequestParam(value = "id") Integer id, Model model,
			HttpServletResponse response) throws IOException, ParseException {
		int success = 0;
		/*
		 * Extract required entities from Data transfer object
		 */
		UserProfile profile = userProfileService.getProfile(profiledto, file);
		Education education = userProfileService.getEducation(profiledto, profile);
		Location location = userProfileService.getLocation(profiledto, profile);
		Experience experience = userProfileService.getExperience(profiledto, profile);

		// insert new profile entity into database
		success = profileService.createProfile(profile, location, education, experience, id);
		if (success > 0) { // if insertion success
			registerService.getUser(profile.getUser().getUser_id()).ifPresent(the_user -> { // add user information into
																							// cookie for the sake of
																							// user accessibility
				addCookieForUser(the_user.getEmail(), the_user.getPassword(), the_user.getUser_id(), response);
				String imageString = null;
				try {
					// Transfer byte code into base64 string to insert into html image tag
					imageString = Base64Utils.encodeToString(file.getBytes());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				// Set model attribute to apply in profile page
				model.addAttribute("profile", profile);
				model.addAttribute("edu", List.of(education));
				model.addAttribute("loca", location);
				model.addAttribute("exp", List.of(experience));
				model.addAttribute("imageString", imageString);
				model.addAttribute("email", the_user.getEmail());
				model.addAttribute("password", the_user.getPassword());
			});
			return "profile";
		}
		return "setUpProfile";
	}

	@PostMapping(value = "/profileLogin")
	public String profileLogin(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password, HttpServletResponse response) {

		List<User> users = registerService.findUser(email, password);

		// If there is no user match, display errors
		if (users.isEmpty()) {
			model.addAttribute("error", "true");
			return "login";
		}
		User user = users.get(0);
		if (user != null) {
			/*
			 * If user exists, search for user profile
			 */

			if (profileService.getUserProfile(user.getUser_id()).isEmpty()) {
				// If user hasn't created the profile

				addCookieForUser(email, password, user.getUser_id(), response);
				model.addAttribute("id", user.getUser_id());
				return "setUpProfile";
			}
			UserProfile userProfile = profileService.getUserProfile(user.getUser_id()).get(0);
			if (userProfile != null) {
				Location location = profileService.getLocation(userProfile.getLocation().getL_id());
				List<Education> education = profileService.getEducationbd(userProfile.getUser_profile_id());
				List<Experience> experience = profileService.getExp(userProfile.getUser_profile_id());
				String imageString = Base64Utils.encodeToString(userProfile.getProfileImage());
				model.addAttribute("profile", userProfile);
				model.addAttribute("edu", education);
				model.addAttribute("loca", location);
				model.addAttribute("exp", experience);
				model.addAttribute("imageString", imageString);
				/*
				 * Set cookie to store user email and password
				 */
				addCookieForUser(user.getEmail(), user.getPassword(), user.getUser_id(), response);
				model.addAttribute("email", email);
				model.addAttribute("password", password);
				return "profile";
			}
		}
		return "setUpProfile";
	}

	// Controller to display profile
	@GetMapping(value = "/profile")
	public String profile(Model model, HttpServletRequest request) {
		User user = null;
		UserProfile userProfile = null;
		String email = "";
		String password = "";
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
			}

			List<User> users = registerService.findUser(email, password);
			if (!users.isEmpty()) {
				user = users.get(0);
				if (profileService.getUserProfile(user.getUser_id()).isEmpty()) {
					return "setUpProfile";
				}

				userProfile = profileService.getUserProfile(user.getUser_id()).get(0);
				Location location = profileService.getLocation(userProfile.getLocation().getL_id());
				List<Education> education = profileService.getEducationbd(userProfile.getUser_profile_id());
				List<Experience> experience = profileService.getExp(userProfile.getUser_profile_id());
				String imageString = Base64Utils.encodeToString(userProfile.getProfileImage());
				model.addAttribute("profile", userProfile);
				model.addAttribute("edu", education);
				model.addAttribute("loca", location);
				model.addAttribute("exp", experience);
				model.addAttribute("imageString", imageString);
				model.addAttribute("email", email);
				model.addAttribute("password", password);
				return "profile";
			}
		}
		return "home";
	}

	// Controller for logout functionality
	@GetMapping("/logout")
	public String logoutUser(Model model, HttpServletResponse response) {
		Cookie emailCookie = new Cookie("email", null);
		emailCookie.setMaxAge(0);
		response.addCookie(emailCookie);
		
		Cookie passwordCookie = new Cookie("password", null);
		passwordCookie.setMaxAge(0);
		response.addCookie(passwordCookie);
		
		Cookie idCookie = new Cookie("id", null);
		idCookie.setMaxAge(0);
		response.addCookie(idCookie);
		
		model.addAttribute("email", "");
		model.addAttribute("password", "");
		model.addAttribute("id", "");
		return "login";
	}

	@PostMapping(value = "/search")
	public String search(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "search") String query, Model model) {

		if (query.length() > 0) {

			if (page == 0) {
				profileCount.setTotalRows(profileService.getTotalProfiles(query));
			}

			if (profileCount.getTotalRows() > 0) {
				List<Location> locations = new ArrayList<>();
				List<String> imageStrings = new ArrayList<>();
				Pageable pageable = PageRequest.of(limitPage(profileCount.getTotalRows(), page), 6,
						Sort.by("firstName"));
				List<UserProfile> userProfiles = profileService.searchUser(query, pageable);
				if (userProfiles.size() > 0) {
					userProfiles.forEach(userProfile -> {
						Location location = profileService.getLocation(userProfile.getLocation().getL_id());
						String imageString = Base64Utils.encodeToString(userProfile.getProfileImage());
						imageStrings.add(imageString);
						if (location != null) {
							locations.add(location);
						}
					});

					model.addAttribute("userprofiles", userProfiles);
					model.addAttribute("imageSources", imageStrings);
					model.addAttribute("locations", locations);
					model.addAttribute("page", page);
					model.addAttribute("query", query);
					model.addAttribute("totalRows", profileCount.getTotalRows() / 6);
				}
			} else {
				model.addAttribute("notFound", "true");
			}
		}

		return "searchList";
	}

	@GetMapping(value = "/search")
	public String searchNext(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
			@RequestParam(value = "query", required = false) String query, Model model) {

		if (query.length() > 0) {
			if (page == 0 || profileCount.getTotalRows() == -1) {
				profileCount.setTotalRows(profileService.getTotalProfiles(query));
			}

			if (profileCount.getTotalRows() > 0) {
				List<Location> locations = new ArrayList<>();
				List<String> imageStrings = new ArrayList<>();
				Pageable pageable = PageRequest.of(limitPage(profileCount.getTotalRows(), page), 6,
						Sort.by("firstName"));
				List<UserProfile> userProfiles = profileService.searchUser(query, pageable);
				userProfiles.forEach(userProfile -> {
					Location location = profileService.getLocation(userProfile.getLocation().getL_id());
					String imageString = Base64Utils.encodeToString(userProfile.getProfileImage());
					imageStrings.add(imageString);
					if (location != null) {
						locations.add(location);
					}
				});
				model.addAttribute("userprofiles", userProfiles);
				model.addAttribute("imageSources", imageStrings);
				model.addAttribute("locations", locations);
				model.addAttribute("page", page);
				model.addAttribute("query", query);
				model.addAttribute("totalRows", profileCount.getTotalRows() / 6);
			} else {
				model.addAttribute("notFound", "true");
			}
		}
		return "searchList";
	}

	@GetMapping("/otherProfile/{id}")
	public String getOtherUserProfile(@PathVariable("id") Integer id, Model model,
			@RequestParam(value = "query", required = false) String query) {
		profileService.getProfile(id).ifPresent(userProfile1 -> {
			Location location = profileService.getLocation(userProfile1.getLocation().getL_id());
			List<Education> education = profileService.getEducationbd(userProfile1.getUser_profile_id());
			List<Experience> experience = profileService.getExp(userProfile1.getUser_profile_id());
			Integer user_id = userProfile1.getUser().getUser_id();
			String imageString = Base64Utils.encodeToString(userProfile1.getProfileImage());
			model.addAttribute("profile", userProfile1);
			model.addAttribute("edu", education);
			model.addAttribute("loca", location);
			model.addAttribute("exp", experience);
			model.addAttribute("imageString", imageString);
			model.addAttribute("query", query);
			model.addAttribute("user_id", user_id);
		});
		return "otherProfile";
	}

	@GetMapping("/findJob")
	public String findJob(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model,
			HttpServletRequest request) {
		String email = null;
		String password = null;
		String id = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("id")) {
					id = cookie.getValue();
				}
			}
		}

		if (page == 0) {
			jobCount.setNumberOfJobs(jobService.getTotaljobs());
		}

		if (jobCount.getNumberOfJobs() <= 0) {
			model.addAttribute("empty", "true");
			return "jobLists";
		}

		Pageable pageable = PageRequest.of(limitPage(jobCount.getNumberOfJobs(), page), 6);
		List<Job> jobs = jobService.getJobs(pageable).getContent();
		model.addAttribute("jobs", jobs);
		model.addAttribute("page", page);
		model.addAttribute("totalRows", jobCount.getNumberOfJobs() / 6);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("id", id);

		return "jobLists";
	}

	@RequestMapping(value = "/savedJob", method = RequestMethod.POST)
	@ResponseBody
	public SavedResponse savedJob(@RequestParam(name = "uid", required = false) Integer uid,
			@RequestParam(name = "jid", required = false) Integer jid) {

		SavedResponse savedResponse = new SavedResponse();

		if (!jobService.checkSavedJob(jid, uid).isEmpty()) {
			savedResponse.setMessage("fail");
			return savedResponse;
		}

		User user = registerService.getUser(uid).get();
		Job job = jobService.getJob(jid);
		SaveJob saveJob = jobService.saveJob(user, job);
		if (saveJob != null) {
			savedResponse.setMessage("success");
			return savedResponse;
		}
		savedResponse.setMessage("fail");
		return savedResponse;

	}

	@RequestMapping(value = "myJobs", method = RequestMethod.GET)
	public String retrieveMyJobs(Model model, HttpServletRequest request) {

		List<Job> jobs = null;
		String email = null;
		String password = null;
		String id = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("id")) {
					id = cookie.getValue();
				}
			}
		}
		
		if(id != null) {
			jobs = jobService.retrieveMyJobs(Integer.parseInt(id));
		}
		
		if(jobs == null) {
			return "login";
		}
		
		if(jobs.isEmpty()) {
			model.addAttribute("empty", "true");
			return "myJob";
		}
		
		model.addAttribute("jobs", jobs);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("id", id);
		return "myJob";
		
	}
	
	@RequestMapping(value = "jobDetail", method = RequestMethod.GET)
	public String getJob(Model model,
			@RequestParam("jid") Integer jid,
			HttpServletRequest request) {
		
		List<Job> jobs = null;
		String email = null;
		String password = null;
		String id = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equalsIgnoreCase("id")) {
					id = cookie.getValue();
				}
			}
		}
		
		Job job = jobService.getJob(jid);
		jobs = jobService.getOtherJob(jid);
		
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("id", id);
		model.addAttribute("job", job);
		model.addAttribute("jobs", jobs);
		
		return "jobDetail";
	}
	
	@RequestMapping(value = "deleteJob", method =  RequestMethod.POST)
	@ResponseBody
	public String deleteSavedJob(Model model, 
			@RequestParam("uid") Integer uid,
			@RequestParam("jid") Integer jid) {
		
		jobService.deleteSaveJob(uid, jid);
		return "success";
	}
	
	@RequestMapping(value = "apply" , method = RequestMethod.POST)
	@ResponseBody
	public String applyJob(Model model,
			@RequestParam("uid") Integer uid,
			@RequestParam("jid") Integer jid) {
		
		if(!jobService.checkJobs(jid, uid).isEmpty()) {
			return "already exist";
		}
		
		Apply apply =  jobService.applyJob(jid, uid);
		if(apply != null) {
			return "success";
		}
		return "fail";
	}
	
	private MimeMessage sendEmailCode(String email, String token) throws MessagingException {
		MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, "utf-8");
		String htmlMsg = "<h3> Dear " + email + ", Thank you for your registration</h3>"
				+ "<p> Please enter below verification code to verify your email </p>" + "<h1>" + token + "</h1>";
		mimeMessageHelper.setText(htmlMsg, true);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject("Verification code from ABC Jobs Company");
		mimeMessageHelper.setFrom("aungthiha12345mdy@gmail.com");
		return mimeMailMessage;
	}

	private void addCookieForUser(String email, String password, Integer id, HttpServletResponse response) {
		Cookie userEmail = new Cookie("email", email);
		userEmail.setMaxAge(3600);
		response.addCookie(userEmail);
		Cookie userPassword = new Cookie("password", password);
		userPassword.setMaxAge(3600);
		response.addCookie(userPassword);
		Cookie idCookie = new Cookie("id", String.valueOf(id));
		idCookie.setMaxAge(3600);
		response.addCookie(idCookie);
	}

	private String generateCode() {
		// Generate Verification Code
		SecureRandom random = new SecureRandom();
		String token = new BigInteger(30, random).toString(32).toUpperCase();
		return token;
	}

	private Integer limitPage(Integer totalRows, Integer currentPage) {
		if ((currentPage * 6) <= totalRows) {
			return currentPage;
		} else
			return 0;
	}

}
