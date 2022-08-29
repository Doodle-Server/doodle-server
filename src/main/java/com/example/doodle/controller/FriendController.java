package com.example.doodle.controller;

import com.example.doodle.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Slf4j
@RestController
@RequiredArgsConstructor
public class FriendController {
    @Autowired
    FriendService friendService;
}
