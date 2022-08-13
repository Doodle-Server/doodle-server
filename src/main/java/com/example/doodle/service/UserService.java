package com.example.doodle.service;

import com.example.doodle.domain.User;
import com.example.doodle.dto.UserDto;
import com.example.doodle.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public User getUserById(String userid) {
        User user = userMapper.getUserById(userid);
        return user;
    }

    public int checkId(String userid) {
        User user = userMapper.getUserById(userid);
        if (user == null) {
            return 0;
        }
        return 1;
    }

    public User CheckUserLogin(UserDto userDto){
        User user = userMapper.getUserById(userDto.getUserid());
        if(user.getUserpw().equals(userDto.getUserpw())){
            return user;
        }
        else{
            return null;
        }
    }
}

