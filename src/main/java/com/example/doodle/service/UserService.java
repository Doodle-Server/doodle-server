package com.example.doodle.service;

import com.example.doodle.dto.UserDTO;
import com.example.doodle.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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

    public int loginCheck(String userid, String userpw_test) {

        String userpw = userMapper.getUserpw(userid);
        int isPassed = userpw.equals(userpw_test)?1:0;
        return isPassed;
    }

    public void logout(HttpSession session){

        session.invalidate();//세션을 초기화!

    }

    public String findId(HttpServletResponse response, String email) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = userMapper.findId(email);

        if (id == null) {
            out.println("alert('가입된 아이디가 없습니다.');");
            out.close();
            return null;
        } else {
            return id;
        }
    }


    // 회원탈퇴
    public boolean deleteUser(UserDTO userDTO, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        if(userMapper.deleteUser(userDTO) != 1) {
            out.println("alert('회원탈퇴 실패');");
            out.close();
            return false;
        } else {
            return true;
        }
    }
}




