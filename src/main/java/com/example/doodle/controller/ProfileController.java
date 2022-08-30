package com.example.doodle.controller;


import com.example.doodle.dto.UserDTO;
import com.example.doodle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    UserService userService;

    //내정보 보기
    @GetMapping("/users/{userid}")
    public UserDTO getUserProfile(@PathVariable String userid){

        return userService.getUserProfile(userid);
    }

    //회원정보 수정
    @PutMapping("/users/edit")
    public void editUserProfile(@RequestBody UserDTO userDTO){
        userService.editUserProfile(userDTO);

    }
}
