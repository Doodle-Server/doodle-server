package com.example.doodle.service;

import com.example.doodle.dto.UserDTO;
import com.example.doodle.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Random;

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


    public int findPwCheck(UserDTO userDTO) throws Exception{
        return userMapper.findPwCheck(userDTO);
    }

    private boolean lowerCheck;
    private int size;
    private String init(){
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;

        do {
            num = ran.nextInt(75) + 48;
            if((num >=48 && num <= 57)||(num >=65 && num <=90)||(num >=97 && num <=122)){
                sb.append((char) num);
            } else continue;
        } while (sb.length() < size);

        if(lowerCheck)
            return sb.toString().toLowerCase();

        return sb.toString();
    }

    public String getKey(boolean lowerCheck, int size){
        this.lowerCheck = lowerCheck;
        this.size = size;

        return init();
    }

    public String findPw(String email,String userId)throws Exception{

        // 임시 비밀번호는 6자리 발급
        String tempPw = getKey(false, 6);

        String userPw = tempPw;

        // 비밀번호 암호화해주는 메서드
        //tempPw = UserSha256.encrypt(tempPw);

        // 데이터 베이스에 저장. (암호화한 값으로 저장?
        userMapper.findPw(email,userId,tempPw);

        return userPw;
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




