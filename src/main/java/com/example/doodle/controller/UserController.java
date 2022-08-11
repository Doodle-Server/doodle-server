package com.example.doodle.controller;

import com.example.doodle.domain.User;
import com.example.doodle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/users/signup", method = RequestMethod.GET)
    public String getsignup(Locale locale, Model model) {

        return "signup";
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String postsignup(User user){
        userService.createUser(user);
        return "index";
    }

    @GetMapping("getUserById.do")
    @ResponseBody
    public String getUserById(@RequestParam String userid) {

        return Integer.toString(userService.checkId(userid));
    }
}
