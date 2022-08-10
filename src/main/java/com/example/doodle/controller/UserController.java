package com.example.doodle.controller;

import com.example.doodle.domain.User;
import com.example.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


@Controller
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getsignup(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate );
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String postsignup(User user){
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/getUserById.do")
    @ResponseBody
    public String getUserById(@RequestParam String userid) {

        return Integer.toString(userService.checkId(userid));
    }
}
