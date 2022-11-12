package com.jobs.controller;

import com.jobs.model.*;
import com.jobs.repository.AdminRepository;
import com.jobs.repository.ProfileRepository;
import com.jobs.services.AdminService;
import com.jobs.services.ProfileService;
import com.jobs.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@ControllerAdvice
public class AdminController {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	ProfileService profileService;

	@Autowired
	AdminService adminService;

	@Autowired
	RegisterService registerService;
	ProfileCount profileCount = new ProfileCount();

	@ModelAttribute("addProfile")
	public AddUserDto addUserDto() {
		return new AddUserDto();
	}

	@ModelAttribute("jobOffer")
	public Job createJob() {
		return new Job();
	}

	@GetMapping("adminZone")
	public String adminZone() {
		return "adminLogin";
	}

	@PostMapping("adminCheck")
	public String adminLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model,
			HttpServletResponse response) {

		List<Admin> admins = adminRepository.checkAdmin(username, password);
		if (admins.size() <= 0) {

			model.addAttribute("error", "true");
			return "adminLogin";
		}
		Cookie userName = new Cookie("username", username);
		userName.setMaxAge(3600);
		response.addCookie(userName);

		Cookie passWord = new Cookie("password", password);
		passWord.setMaxAge(3600);
		response.addCookie(passWord);

		if (profileCount.getTotalRows() < 0) {
			profileCount.setTotalRows((int) profileRepository.count());
		}
		List<Location> locations = new ArrayList<>();
		List<User> users = new ArrayList<>();
		Page<UserProfile> userProfiles = profileRepository
				.findAll(PageRequest.of(limitPage(profileCount.getTotalRows(), page), 10, Sort.by("firstName")));
		List<UserProfile> userProfileList = userProfiles.getContent();

		if (!userProfileList.isEmpty()) { // if the is profile data
			userProfileList.forEach(userProfile -> {

				// Retrieve location lists
				locations.add(profileService.getLocation(userProfile.getLocation().getL_id()));

				// Retrieve user lists
				users.add(registerService.getUser(userProfile.getUser().getUser_id()).get());
			});
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			model.addAttribute("userprofiles", userProfileList);
			model.addAttribute("locations", locations);
			model.addAttribute("users", users);
			model.addAttribute("page", page);
			model.addAttribute("totalRows", profileCount.getTotalRows() / 10);
			return "adminTable";
		}

		return "adminTable";
	}

	@GetMapping("emailConstruction")
	public String emailForm(HttpServletRequest request) {
		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		return "admin";
	}

	@PostMapping("sendEmail")
	public String sendEmail(@RequestParam("toEmail") String email, @RequestParam("subject") String subject,
			@RequestParam("message") String message, Model model, HttpServletRequest request) {

		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}

		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));

		try {
			MimeMessage mimeMessage = contrustMessage(email, subject, message);
			javaMailSender.send(mimeMessage);
			model.addAttribute("success", "true");
			System.out.println("Success is true now");
			return "adminTable";
		} catch (MessagingException e) {
			System.out.println("Mail Server Error");
		}
		return "admin";
	}

	@GetMapping("adminTable")
	public String getUsersInfo(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			Model model, HttpServletRequest request) {

		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}

		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));

		if (profileCount.getTotalRows() < 0) {
			profileCount.setTotalRows((int) profileRepository.count());
		}
		List<Location> locations = new ArrayList<>();
		List<User> users = new ArrayList<>();
		Page<UserProfile> userProfiles = profileRepository
				.findAll(PageRequest.of(limitPage(profileCount.getTotalRows(), page), 10, Sort.by("firstName")));
		List<UserProfile> userProfileList = userProfiles.getContent();

		if (!userProfileList.isEmpty()) { // if there is profile data
			userProfileList.forEach(userProfile -> {

				// Retrieve location lists
				locations.add(profileService.getLocation(userProfile.getLocation().getL_id()));

				// Retrieve user lists
				users.add(registerService.getUser(userProfile.getUser().getUser_id()).get());
			});
			model.addAttribute("userprofiles", userProfileList);
			model.addAttribute("locations", locations);
			model.addAttribute("users", users);
			model.addAttribute("page", page);
			model.addAttribute("totalRows", profileCount.getTotalRows() / 10);
			return "adminTable";
		}
		return "admin";
	}

	@GetMapping("/edit/{id}")
	public String editUserData(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));

		UserProfile userProfile = profileRepository.findById(id).get();
		Location location = profileService.getLocation(userProfile.getLocation().getL_id());
		User user = registerService.getUser(userProfile.getUser().getUser_id()).get();
		model.addAttribute("userprofile", userProfile);
		model.addAttribute("location", location);
		model.addAttribute("user", user);
		return "editForm";
	}

	@PostMapping("editedData")
	public String editedData(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("country") String country, @RequestParam("city") String city,
			@RequestParam("p_id") Integer user_profile_id, @RequestParam("u_id") Integer user_id,
			@RequestParam("l_id") Integer l_id, HttpServletRequest request, Model model) {
		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));
		int effectedRows = profileService.editData(email, password, country, city, firstName, lastName, l_id, user_id,
				user_profile_id);
		if (effectedRows <= 0) {
			return "editForm";
		}
		return "redirect:/adminTable";
	}

	@GetMapping("deleteUser")
	public String deleteUser(@RequestParam("u_id") Integer user_id, @RequestParam("l_id") Integer location_id,
			@RequestParam("p_id") Integer profile_id, HttpServletRequest request, Model model) {

		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}

		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));
		profileService.delectUser(profile_id, user_id, location_id);
		return "redirect:/adminTable";
	}

	@GetMapping("addUser")
	public String addUserForm(Model model, HttpServletRequest request) {
		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));
		return "addUser";
	}

	@GetMapping("adminLogout")
	public String logout(Model model, HttpServletResponse response) {

		Cookie userName = new Cookie("username", "");
		userName.setMaxAge(0);
		response.addCookie(userName);

		Cookie passWord = new Cookie("password", "");
		passWord.setMaxAge(0);
		response.addCookie(passWord);

		return "adminLogin";
	}

	@PostMapping("addUser")
	public String addUser(Model model, @ModelAttribute("addProfile") AddUserDto addUserDto,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		User user = new User();
		user.setEmail(addUserDto.getEmail());
		user.setPassword(addUserDto.getPassword());
		user = registerService.save(user);
		Location location = new Location();
		location.setCity(addUserDto.getCity());
		location.setCountry(addUserDto.getCountry());
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(addUserDto.getFirstName());
		userProfile.setLastName(addUserDto.getLastName());
		userProfile.setProfileImage(file.getBytes());
		userProfile.setSelf_description(addUserDto.getSelf_description());
		Education education = new Education();
		education.setSchool(addUserDto.getSchool());
		education.setQualification(addUserDto.getQualification());
		education.setUser_profile(userProfile);
		Experience experience = new Experience();
		experience.setCompany(addUserDto.getCompany());
		experience.setStart_data(simpleDateFormat.parse(addUserDto.getStart_data()));
		experience.setEnd_date(simpleDateFormat.parse(addUserDto.getEnd_date()));
		experience.setPosition(addUserDto.getPosition());
		experience.setCountry(addUserDto.getCountry());
		experience.setUser_profile(userProfile);
		int result = profileService.createProfile(userProfile, location, education, experience, user.getUser_id());
		System.out.println(result);
		model.addAttribute("username", validatedResult.get("username"));
		model.addAttribute("password", validatedResult.get("password"));
		return "redirect:/adminTable";
	}

	@PostMapping("postJob")
	public String postJob(Model model, @ModelAttribute("jobOffer") Job job, HttpServletRequest request) {

		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		job = adminService.saveJob(job);
		if (job != null) {
			model.addAttribute("success", "true");
		}
		return "postJob";

	}

	@GetMapping("jobForm")
	public String getJobForm(Model model, HttpServletRequest request) {
		HashMap<String, String> validatedResult = securityCheck(request);
		if (validatedResult.get("status").equals("false")) {
			return "redirect:/adminZone";
		}
		model.addAttribute("success", "false");
		return "postJob";
	}

	private MimeMessage contrustMessage(String email, String subject, String message) throws MessagingException {
		MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, "utf-8");
		String htmlMsg = "<h3> Dear " + email + ", please let us introduce our job portal platform</h3>" + "<p>"
				+ message + "</p>" + "<a href='http://localhost:8085/ABC_Jobs/registration'>"
				+ "<h1 style='color:style;'>" + "Register Now" + "</h1></a>";
		mimeMessageHelper.setText(htmlMsg, true);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setFrom("aungthiha12345mdy@gmail.com");
		return mimeMailMessage;
	}

	private Integer limitPage(Integer totalRows, Integer currentPage) {
		if ((currentPage * 10) <= totalRows) {
			return currentPage;
		} else
			return 0;
	}

	private HashMap<String, String> securityCheck(HttpServletRequest request) {

		HashMap<String, String> valueMap = new HashMap<>();
		String username = "";
		String password = "";
		if (request.getCookies() != null) { // Acquire token from cookie
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equalsIgnoreCase("username")) {
					username = cookie.getValue();
				}

				if (cookie.getName().equalsIgnoreCase("password")) {
					password = cookie.getValue();
				}
			}
		}

		if (username.isEmpty() || password.isEmpty()) {
			valueMap.put("status", "false");
			return valueMap;
		}
		valueMap.put("status", "true");
		valueMap.put("username", username);
		valueMap.put("password", password);
		return valueMap;
	}
}
