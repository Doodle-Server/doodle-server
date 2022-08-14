package com.example.doodle.service;

import com.example.doodle.dto.UserDTO;
import com.example.doodle.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createUser(UserDTO userDTO) {
        userMapper.createUser(userDTO);
    }

    public String getUsernameById(String userid) {
        UserDTO userDTO = userMapper.getUserById(userid);
        return userDTO.getUsername();
    }

    public String loginCheck(UserDTO userDTO, HttpSession session) {

        String name = userMapper.loginCheck(userDTO);
        if (name != null) {
            session.setAttribute("userid", userDTO.getUserid());
            session.setAttribute("name",name);
        } //null리턴..주의

        return name;
    }

    public void logout(HttpSession session){

        session.invalidate();//세션을 초기화!

    }
}




