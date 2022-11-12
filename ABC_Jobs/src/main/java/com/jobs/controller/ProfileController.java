package com.jobs.controller;

import com.jobs.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@ControllerAdvice
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/editProfile")
    public String editProfile(@RequestParam("file") MultipartFile file,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("country") String country,
                              @RequestParam("city") String city,
                              @RequestParam("selfSummary") String self_description,
                              @RequestParam("u_id") Integer u_id,
                              @RequestParam("l_id") Integer l_id) throws SQLException, IOException {

        int effectedRow = 0;
        if(file.isEmpty()){ // if the image file is not inputted, set image variable to null
            effectedRow = profileService.editProfile(firstName,lastName,country,city,
                    u_id, l_id, null, self_description);
        }else { //else insert edited image file
            effectedRow = profileService.editProfile(firstName,lastName,country,city, u_id, l_id,
                    file.getBytes(), self_description);
        }
        System.out.println("Total rows " + effectedRow + " have been edited from user profile and location table");
        return "redirect:/profile";
    }

    @PostMapping("/eduDesc")
    public String editDesc(
            @RequestParam("selfSummary") String self_description,
            @RequestParam("u_id") Integer user_profile_id
    ){
        int effectedRow = profileService.editSelfDescription(self_description, user_profile_id);
        System.out.println("Total rows " + effectedRow + " have been edited from self description column from user profile table");
        return "redirect:/profile";
    }
}
