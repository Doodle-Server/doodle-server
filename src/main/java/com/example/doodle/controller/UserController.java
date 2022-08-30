package com.example.doodle.controller;


import com.example.doodle.dto.FriendDTO;
import com.example.doodle.dto.UserCheckDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.exception.ApiRequestException;
import com.example.doodle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;


//    @GetMapping("/home")
//    public String home(){
//        return "home";
//    }
//    @RequestMapping(value = "/users/signup", method = RequestMethod.GET)
//    public ModelAndView getsignup() {
//
//        return new ModelAndView("signup");
//    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String postsignup(@ModelAttribute UserDTO userDTO){
        userService.createUser(userDTO);
        log.info(userDTO.getUsername(), userDTO.getUserpw());
        return "signup";
    }



    @GetMapping("/users/login")
    public String getlogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

//        이미 로그인 된 상태면 home으로 redirect
        if(userid==null){
            throw new ApiRequestException("이미 로그인 된 상태입니다.");
        }
        else{
            return "redirect:/home";
        }

    }

    @PostMapping("/users/login")
    public void postlogin(@RequestParam String userid, @RequestParam String userpw_test, HttpServletRequest request){

        int isPassed = userService.loginCheck(userid, userpw_test);

        if(isPassed==1) {//아이디와 비번 일치하는 경우
            HttpSession session = request.getSession();
            session.setAttribute("userid", userid);
            log.info("로그인 성공");
        }

        else{
            log.info("로그인 실패");

        }

    }

    @GetMapping("/users/logout")
    public String logout(HttpSession session){
        userService.logout(session);
        log.info("로그아웃 성공");
        return "redirect:/home";
    }



    //아이디 찾기
    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public String findId(HttpServletResponse response, @RequestParam("email") String email, Model md) throws Exception{
        md.addAttribute("id", userService.findId(response, email));
        return "redirect:/findId";
    }

    /*@RequestMapping(value="/findPw" , method=RequestMethod.GET)
    public String findPwView() throws Exception{
        return"/users/findPwView";
    }*/

    @RequestMapping(value="/findPw", method=RequestMethod.POST)
    public void findPw(UserDTO userDTO,Model model) throws Exception{
        log.info("memberPw"+userDTO.getUserid());

        //아이디+이메일 맞는지 확인
        if(userService.findPwCheck(userDTO)==0) {
            log.info("pwCheck");
            model.addAttribute("msg", "아이디와 이메일을 확인해주세요");
        } else {
            userService.findPw(userDTO.getEmail(),userDTO.getUserid());
            model.addAttribute("member", userDTO.getEmail());

        }
    }


    @DeleteMapping( "/users/deleteUser")
    public void delete_form(@RequestBody UserCheckDTO userCheckDTO, HttpSession session, HttpServletResponse response) throws Exception{
        if(userService.deleteUser(userCheckDTO.getUserid(),userCheckDTO.getUserpw_test(), response)) {
            session.invalidate();
        }
//        return "redirect:/home";
    }



}
