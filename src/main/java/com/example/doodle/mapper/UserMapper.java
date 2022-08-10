package com.example.doodle.mapper;

import com.example.doodle.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void signup(User user);
    User getUserById(String userid);
}
