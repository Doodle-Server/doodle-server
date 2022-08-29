package com.example.doodle.service;

import com.example.doodle.mapper.FriendMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FriendService {
    @Autowired
    FriendMapper friendMapper;
}
