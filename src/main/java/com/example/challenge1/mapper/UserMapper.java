package com.example.challenge1.mapper;

import com.example.challenge1.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    void signup(User user);
    User getUserById(String userid);
}
