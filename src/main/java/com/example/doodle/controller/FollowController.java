/*package com.example.doodle.controller;


import com.example.doodle.dto.FollowDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FollowController {


    private final FollowService followService;

    @PostMapping("/friends/{relatedUserID}")
    public FollowDTO followUser(@PathVariable long relatedUserID, Authentication authentication) {
        return followService.save(authentication.getName(), relatedUserID);
    }


    @DeleteMapping("/friends/{relatedUserID}")
    public void unfollowUser(@PathVariable long relatedUserID, Authentication authentication){
        Long id = followService.getFollowId;
    }

}*/
