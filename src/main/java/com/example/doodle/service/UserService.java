package com.example.doodle.service;

import com.example.doodle.domain.User;
import com.example.doodle.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void join(User user) {
        userMapper.signup(user);
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
}
