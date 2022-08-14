package com.example.doodle.controller;

import com.example.doodle.domain.User;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping(value = "/users/signup", method = RequestMethod.GET)
    public ModelAndView getsignup() {

        return new ModelAndView("signup");
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String postsignup(@ModelAttribute UserDTO userDTO){
        userService.createUser(userDTO);
        log.info(userDTO.getUsername(), userDTO.getUserpw());
        return "signup";
    }

    @GetMapping("getUsernameById.do")
    @ResponseBody
    public String getUsernameById(@RequestParam String userid) {

        return userService.getUsernameById(userid);
    }

    @GetMapping("/users/login")
    public String getlogin(){
        return "login";
    }

    @PostMapping("/users/login")
    public ModelAndView postlogin(@ModelAttribute UserDTO userDTO, HttpSession session){
        String name = userService.loginCheck(userDTO, session);
        ModelAndView mv = new ModelAndView();

        //이미 로그인 된 경우 거르기
        if(session.getAttribute("login")!=null){
            log.info("로그인 되어 있음");
            mv.setViewName("redirect:/home");
        }

        //로그인 성공 또는 실패하는 경우
        if(name!=null){
            log.info("로그인 성공");
            session.setAttribute("login",userDTO );
            mv.setViewName("redirect:/home"); //로그인 성공시 메인화면으로 (일단 home으로 해둠)
        }
        else{
            log.info("로그인 실패");
            mv.setViewName("redirect:/users/login");
            mv.addObject("message", "error");
        }

        return mv;

    }

    @GetMapping("/users/logout")
    public String getlogout(HttpSession session){
        if(session.getAttribute("login")==null){
            log.info("로그인 된 상태 아님");
            return "redirect:/home";
        }
        userService.logout(session);
        log.info("로그아웃 성공");
        return "redirect:/home";
    }

}
