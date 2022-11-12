package com.jobs.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jobs.model.Education;
import com.jobs.model.Experience;
import com.jobs.model.Location;
import com.jobs.model.User;
import com.jobs.model.UserProfile;
import com.jobs.services.ProfileService;
import com.jobs.services.RegisterService;
import com.jobs.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class JobsControllerTest{
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	JobsController joinsController;
	
	@Mock
	RegisterService registerService;
	
	@Mock
	ProfileService profileService;
	
	@Mock
	UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(joinsController).build();
	}
	
	//Start of login test
	@Test
	@DisplayName("Login with incorrect credentials")
	public void testForValidateLogin() throws Exception {
		String incorrectEmail = "daw626@gmail.com";
		String incorrectPassword = "123456789";
		//Find user not found
		when(registerService.findUser(incorrectEmail, incorrectPassword))
		.thenReturn(new ArrayList<User>());
		mockMvc.perform(MockMvcRequestBuilders.post("/profileLogin")
				.param("email", incorrectEmail)
				.param("password", incorrectPassword))
		.andExpect(MockMvcResultMatchers.model().attribute("error", "true"))
		.andExpect(MockMvcResultMatchers.view().name("login"))
		.andReturn();
	}
	
	@Test
	@DisplayName("Login with correct credentials")
	public void testForLogin() {
		String correctEmail = "aung@gmail.com";
		String correctPassword = "##@#232323";
		User user = new User();
		user.setEmail(correctEmail);
		user.setPassword(correctPassword);
		user.setUser_id(10);
		when(registerService.findUser(correctEmail,correctPassword))
		.thenReturn(List.of(user));
		
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName("Aung Thiha");
		userProfile.setLastName("Tun");
		userProfile.setProfileImage(new byte[] {0,0,0,0,1});
		
		when(profileService.getUserProfile(any()))
		.thenReturn(List.of(userProfile));
		
		when(profileService.getLocation(userProfile.getUser_profile_id()))
		.thenReturn(new Location());
		
		when(profileService.getEducationbd(userProfile.getUser_profile_id()))
		.thenReturn(List.of(new Education()));
		
		when(profileService.getExp(userProfile.getUser_profile_id()))
		.thenReturn(List.of(new Experience()));
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/profileLogin")
					.param("email", correctEmail)
					.param("password", correctPassword))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attribute("profile", userProfile))
			.andExpect(MockMvcResultMatchers.view().name("profile"))
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//End of login test
	
	//Start of registration test
	@Test
	@DisplayName("Test for duplicated email registration")
	public void testForDuplicatedEmailRegister() {
		
		User user = new User();
		user.setEmail("aung@gmail.com");
		user.setPassword("afddflksdjfjasd");
		when(registerService.findUser(anyString())).thenReturn(List.of(user));
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/verification")
					.param("email", "aung@gmail.com"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attribute("hasAccount", "true"))
			.andExpect(MockMvcResultMatchers.view().name("registration"))
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test email confirmation before register user data into database")
	public void testForConfirmation() {
		String email = "aung@gmail.com";
		String password = "24342@@##";
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		when(registerService.findUser(email)).thenReturn(new ArrayList<User>());
		when(userService.register(any())).thenReturn(user);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/verification")
					.param("email", email))
					.andExpect(MockMvcResultMatchers.view().name("confirmation"))
					.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test for registration with correct data and verification code")
	public void registerAnAccount() {
		//Demo data
		String email = "aung@gmail.com";
		String password = "23432423##";
		Integer user_id = 10;
		String generatedCode = "1FAD23";
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUser_id(user_id);
		
		//Token cookie (generated verification code)
		Cookie token = new Cookie("token", generatedCode);
		token.setMaxAge(3600);
		
		//Mock user data save to database
		when(registerService.save(any())).thenReturn(user);
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/nextStep")
					.param("email", email)
					.param("ps", password)
					.param("code", generatedCode)
					.cookie(token))
			.andExpect(MockMvcResultMatchers.model().attribute("id", user.getUser_id()))
			.andExpect(MockMvcResultMatchers.view().name("thankyou"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//End of registration test
	
	
	//Start of Search Test
	@Test
	@DisplayName("Test for search functionalities with correct search query")
	public void testForSearchUser() throws Exception {
		String query = "aung";
		Pageable pageable = PageRequest.of(0, 6, Sort.by("firstName"));
		Location location = new Location();
		location.setCity("MDY");
		location.setCountry("MYAN");
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName("Aung Thiha Tun");
		userProfile.setLastName("Harrison");
		userProfile.setLocation(location);
		userProfile.setExperiences(List.of(new Experience()));
		userProfile.setEducation(List.of(new Education()));
		userProfile.setProfileImage(new byte[] {1,0,0,0,0,0,0});
		List<UserProfile> userProfiles = List.of(userProfile);
		when(profileService.searchUser(query, pageable)).thenReturn(userProfiles);
		when(profileService.getTotalProfiles(anyString())).thenReturn(2);
		when(profileService.getLocation(anyInt())).thenReturn(location);
		mockMvc.perform(MockMvcRequestBuilders.post("/search")
				.param("search",query))
				.andExpect(MockMvcResultMatchers.model().attribute("userprofiles", userProfiles	))
				.andExpect(MockMvcResultMatchers.view().name("searchList"))
				.andReturn();
	}
	
	@Test
	@DisplayName("Test for search functionalities with incorrect query")
	public void testForSearchUserQuery() {
		String incorrectQuery = "23fasdfadf";
		when(profileService.getTotalProfiles(incorrectQuery)).thenReturn(0);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/search").param("search", incorrectQuery))
			.andExpect(MockMvcResultMatchers.model().attribute("notFound", "true"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//End of search test
	
	//Start of Logout test
	@Test
	@DisplayName("Test for logout")
	public void testForlogout() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attribute("email", ""))
			.andExpect(MockMvcResultMatchers.model().attribute("password", ""))
			.andExpect(MockMvcResultMatchers.view().name("login"))
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//End of Logout test
	
	
	//Start of Reset password test
	@Test
	@DisplayName("Test for reset password with correct credential")
	public void testForResetPassword() throws Exception  {
		String resettedPwd = "213213232#$#@$#@$";
		String emailAddress = "aungthihatun@gmail.com";
		when(registerService.updatePassword(resettedPwd, emailAddress))
		.thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/resetSuccess")
				.param("email", emailAddress)
				.param("password", resettedPwd))
		.andExpect(MockMvcResultMatchers
				.view()
				.name("resetSuccessful"))
		.andExpect(MockMvcResultMatchers
				.status()
				.is2xxSuccessful());
	}
	
	@Test
	@DisplayName("Test for reset password with incorrect verification code")
	public void testForResetPasswordFail() throws Exception  {
		String incorrectCode = "1AZAD";
		String emailAddress = "aungthihatun@gmail.com";
		Cookie generatedToken = new Cookie("token", "11FFGG");
		generatedToken.setMaxAge(3600);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/nextStep")
				.param("email", emailAddress)
				.param("code", incorrectCode)
				.param("mode", "login")
				.cookie(generatedToken))
		.andExpect(MockMvcResultMatchers.model().attribute("error", "true"))
		.andExpect(MockMvcResultMatchers.view().name("confirmation"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	@DisplayName("Test whether there is email verification")
	public void testForVerificationTest() {
		Cookie token = new Cookie("token", "1FACD");
		token.setMaxAge(3600);
		Cookie[] cookies = new Cookie[] {token};
		try {
			mockMvc.perform(MockMvcRequestBuilders
					.post("/nextStep")
					.param("mode", "login")
					.param("code", "1FACD")
					.cookie(cookies))
			.andExpect(MockMvcResultMatchers
					.status()
					.isOk())
			.andExpect(MockMvcResultMatchers
					.view()
					.name("resetPwd"))
			.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//End of reset password
}