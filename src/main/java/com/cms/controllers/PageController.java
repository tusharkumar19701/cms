package com.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.entities.User;
import com.cms.forms.UserForm;
import com.cms.helper.Message;
import com.cms.helper.MessageType;
import com.cms.services.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Value;


@Controller
public class PageController {

    private UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler.");
        model.addAttribute("name","Contact Management System");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin",true);
        System.out.println("About page loading.");
        return "about";
    }

    // services route
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading.");
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String signupPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);

        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session) {
        System.out.println("Processing registration");

        if(rBindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setAbout(userForm.getAbout());
        user.setProfilePic("https://i.pinimg.com/474x/13/74/20/137420f5b9c39bc911e472f5d20f053e.jpg");
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setPassword(userForm.getPassword());


        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        Message message = Message.builder().content("Registration successful").type(MessageType.blue).build();

        session.setAttribute("message", message);

        return "redirect:/register";
    }

}
