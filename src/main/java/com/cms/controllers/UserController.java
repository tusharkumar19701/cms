package com.cms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.helper.Helper;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        
        logger.info("User logged in {}: ", username);

        return "user/profile";
    }
}
