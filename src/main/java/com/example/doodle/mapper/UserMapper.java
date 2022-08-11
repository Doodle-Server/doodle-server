package com.example.doodle.mapper;

import com.example.doodle.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    void createUser(User user);
    User getUserById(String userid);
    List<Map<String, Object>> findAll();
}
