package com.example.doodle.mapper;

import com.example.doodle.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    void createUser(UserDTO userDTO);
    UserDTO getUserById(String userid);

    UserDTO getUserByNickname(String nickname);
    List<Map<String, Object>> findAll();
    String loginCheck(UserDTO userDTO);

    String findId(String email);
    int findPwCheck(UserDTO userDTO);
    String findPw(String email,String userid, String userpw);
    int deleteUser(String userid);

    String getUserpw(String userid);

    UserDTO getProfileById(String userid);

    void editUserProfile(UserDTO userDTO);
}
