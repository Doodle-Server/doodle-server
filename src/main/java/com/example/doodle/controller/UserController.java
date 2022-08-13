package com.example.doodle.controller;

import com.example.doodle.domain.User;
import com.example.doodle.dto.UserDto;
import com.example.doodle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/users/signup", method = RequestMethod.GET)
    public ModelAndView getsignup() {

        return new ModelAndView("signup");
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String postsignup(@RequestParam User user){
        userService.createUser(user);
        log.info(user.getUsername(), user.getUserpw());
        return "signup";
    }

    @GetMapping("getUserById.do")
    @ResponseBody
    public String getUserById(@RequestParam String userid) {

        return Integer.toString(userService.checkId(userid));
    }

//    public String login(UserDto user){
//
//    };
}
