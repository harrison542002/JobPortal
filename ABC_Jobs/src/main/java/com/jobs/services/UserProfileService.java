package com.jobs.services;

import com.jobs.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserProfileService {

    public  UserProfile getProfile(UserProfileDto userdto, MultipartFile file) {
        /*
        Set user profile object by using dto
        */
        UserProfile userProfile;
        try{
            userProfile = new UserProfile();
            userProfile.setFirstName(userdto.getFirstName());
            userProfile.setLastName(userdto.getLastName());
            userProfile.setSelf_description(userdto.getSelf_description());
            userProfile.setProfileImage(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userProfile;
    }

    public Experience getExperience(UserProfileDto userProfileDto, UserProfile userProfile) throws ParseException{

    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Experience experience = new Experience();
        experience.setCompany(userProfileDto.getCompany());
        experience.setCountry(userProfileDto.getWorkedCountry());
        experience.setPosition(userProfileDto.getPosition());
        experience.setEnd_date(sf.parse(userProfileDto.getEnd_date()));
        experience.setStart_data(sf.parse(userProfileDto.getStart_data()));
        experience.setUser_profile(userProfile);
        return  experience;
    }

    public Location getLocation(UserProfileDto userProfileDto, UserProfile userProfile){
        Location location =  new Location();
        location.setCountry(userProfileDto.getCountry());
        location.setCity(userProfileDto.getCity());
        location.setUserProfile(userProfile);
        return location;
    }

    public Education getEducation(UserProfileDto userProfileDto, UserProfile userProfile){
        Education edu;
        edu = new Education();
        edu.setQualification(userProfileDto.getQualification());
        edu.setSchool(userProfileDto.getSchool());
        edu.setUser_profile(userProfile);
        return  edu;
    }
}
