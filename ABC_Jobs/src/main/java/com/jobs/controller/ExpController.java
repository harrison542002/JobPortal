package com.jobs.controller;


import com.jobs.model.Experience;
import com.jobs.model.UserProfile;
import com.jobs.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExpController {
    @Autowired
    ProfileService profileService;

    @PostMapping("/insertExp")
    public String insertExp(
            @RequestParam("company") String company,
            @RequestParam("position") String position,
            @RequestParam("start_data") String start_data,
            @RequestParam("end_date") String end_date,
            @RequestParam("country") String country,
            HttpServletRequest request
    ) throws ParseException {
        /*
        Handler for inserting new experience
         */
        String email = "";
        String password = "";
        Integer id = null;
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
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
                Experience experience = new Experience();
                experience.setUser_profile(userProfile);
                experience.setPosition(position);
                experience.setCountry(country);
                experience.setEnd_date(simpleDateFormat.parse(end_date));
                experience.setStart_data(simpleDateFormat.parse(start_data));
                experience.setCompany(company);
                profileService.saveExp(experience);
                return "redirect:/profile";
            }
        }
        return "redirect:/profile";
    }

    @PostMapping(value = "/editExp/{id}")
    public String editEdu(
            @PathVariable Integer id,
            @RequestParam("company") String company,
            @RequestParam("position") String position,
            @RequestParam("start_data") String start_data,
            @RequestParam("end_date") String end_date,
            @RequestParam("country") String country) {
        /*
        Handler for editing existing experience
         */
        int effectedRow = profileService.editExperience(company, country, position, start_data, end_date, id);
        if (effectedRow > 0) {
            return "redirect:/profile";
        }
        return "redirect:/profile";
    }
}
