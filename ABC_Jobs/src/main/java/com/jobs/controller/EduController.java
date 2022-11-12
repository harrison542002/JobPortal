package com.jobs.controller;

import com.jobs.model.Education;
import com.jobs.model.UserProfile;
import com.jobs.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class EduController {
    @Autowired
    ProfileService profileService;

    @PostMapping(value = "/profileEdu")
    public String addEdu(@RequestParam("school") String school,
                                @RequestParam("qualification") String qualification,
                                HttpServletRequest request) {
        String email = "";
        String password = "";
        Integer id = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase("email")) {
                	
                    email = cookie.getValue();
                }
                if (cookie.getName().equalsIgnoreCase("password")) {
                    password = cookie.getValue();
                }
                if (cookie.getName().equalsIgnoreCase("id")) {
                    id = Integer.valueOf(cookie.getValue());
                }
            }
            if (email != null && password != null) {
                UserProfile userProfile = profileService.getUserProfile(id).get(0);
                Education education = new Education();
                education.setSchool(school);
                education.setQualification(qualification);
                education.setUser_profile(userProfile);
                profileService.saveEdu(education);
                return "redirect:/profile";
            }
        }
        return "redirect:/profile";
    }

    @PostMapping(value = "/editEdu/{id}")
    public String editEdu(
            @PathVariable String id,
            @RequestParam("eduschool") String school,
            @RequestParam("eduqualification") String qualification,
            HttpServletRequest request) {


        int effectedRow = profileService.editEdu(school, qualification, Integer.valueOf(id));
        if (effectedRow > 0) {
            return "redirect:/profile";
        }
        return "redirect:/profile";
    }
}
