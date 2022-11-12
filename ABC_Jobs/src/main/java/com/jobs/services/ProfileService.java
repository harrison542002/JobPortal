package com.jobs.services;

import com.jobs.model.*;
import com.jobs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository userRepo;

    @Autowired
    LocationRepository locationRepo;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    RegisterService registerService;
    
    @Autowired
    MessageBoardParticipantRepo messageBoardParticipantRepo;
    
    @Autowired
    MessageBoardRepo messageBoardRepo;

    @Autowired
    RegisterRepository registerRepository;


    @Transactional
    public int createProfile(UserProfile profile,
                             Location location,
                             Education education,
                             Experience experience,
                             Integer id) {
		/*
		Save user profile and related entities such as location, education and experience
		 */
        final User user = registerService.getUser(id).get();
        profile.setLocation(location);
        profile.setUser(user);
        location = locationRepo.save(location);
        profile = userRepo.save(profile);
        education = educationRepository.save(education);
        experience = experienceRepository.save(experience);

        if (profile != null & location != null & education != null & experience != null) {
            return 1;
        }
        return 0;
    }

    public List<UserProfile> getUserProfile(Integer id) {
        return userRepo.getProfile(id);
    }

    public Optional<UserProfile> getProfile(Integer id){
        return userRepo.findById(id);
    }

    public Location getLocation(Integer id) {
        return locationRepo.getLocation(id);
    }

    public List<Education> getEducationbd(Integer id) {
        return educationRepository.getEducation(id);
    }

    public List<Experience> getExp(Integer id) {
        return experienceRepository.getExp(id);
    }

    public Education saveEdu(Education education) {
        return educationRepository.save(education);
    }

    public Experience saveExp(Experience experience){
        return experienceRepository.save(experience);
    }

    public Integer getTotalProfiles(String query){
        return userRepo.getTotal(query);
    }

    public int editEdu(String school, String qualification, Integer id) {
        return educationRepository.updateEdu(school, qualification, id);
    }

    public int editProfile(String firstName, String lastName, String country, String city,
                           Integer u_id, Integer l_id, byte[] profileImage, String self_description) throws SQLException {

        int effectedRows = 0;
        userRepo.findById(u_id).ifPresent(userProfile -> {
            userProfile.setFirstName(firstName);
            userProfile.setLastName(lastName);
            if(profileImage != null) {
                userProfile.setProfileImage(profileImage);
            }
            userProfile.setSelf_description(self_description);
            userRepo.save(userProfile);
        });

        locationRepo.findById(l_id).ifPresent(
                location -> {
                    location.setCountry(country);
                    location.setCity(city);
                    locationRepo.save(location);
                }
        );
        return effectedRows;
    }

    public int editSelfDescription(String self_desc, Integer user_profile_id) {
        int effectedRow = 0;
        effectedRow = userRepo.editSelf_description(self_desc, user_profile_id);
        return effectedRow;
    }

    public int editExperience(String company, String country,
                              String position, String start_data,
                              String end_date, Integer user_profile_id){
        return  experienceRepository.updateExp(company,position,country,start_data,end_date,user_profile_id);
    }

    public int editData(String email, String password, String country, String city, String firstName,
                        String lastName, Integer l_id, Integer u_id, Integer userProfileID){
        int effectedRow = 0;
        effectedRow += locationRepo.editProfile(country, city,l_id);
        effectedRow += registerRepository.editUserInfo(password, email, u_id);
        effectedRow += userRepo.editProfile(firstName,lastName,userProfileID);

        return effectedRow;
    }

    //Search for other user
    public List<UserProfile> searchUser(String query, Pageable pageable){
        return userRepo.getFromSearch(query, pageable);
    }
    
    public List<UserProfile> searchUserFromMessaging(String query){
    	return userRepo.getFromSearchForMessaging(query);
    }

    @Transactional
    public void delectUser(Integer profileID, Integer userID, Integer locationID){

    	List<Integer> mbID = new ArrayList<>();
        experienceRepository.deleteExp(profileID);
        educationRepository.deleteEdu(profileID);
        List<MessageBoard> messageBoards  = messageBoardRepo.retrieveMessageBoard(userID);
        
        if(!messageBoards.isEmpty()) {
        	messageBoards.forEach( messageBoard -> {
        		mbID.add(messageBoard.getMessage_bid());
        	});
        }
        
        if(!mbID.isEmpty()) {
        	mbID.forEach( mb -> {
        		messageBoardParticipantRepo.delecteMSB(mb);
        	});
        }
        messageBoardRepo.delectMessageBoard(userID);
        userRepo.deleteById(profileID);
        locationRepo.deleteById(locationID);
        registerRepository.deleteById(userID);
    }
}
