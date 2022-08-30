package com.example.doodle.controller;

import com.example.doodle.dto.FriendDTO;
import com.example.doodle.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping("/users/{relatingUserID}/friends/{relatedUserID}/request")
    public void followUser(@PathVariable String relatingUserID, @PathVariable String relatedUserID) {
        friendService.requestFriend(relatingUserID, relatedUserID);
    }

    @DeleteMapping("/users/{relatingUserID}/friends/{relatedUserID}/delete")
    public void unfollowUser(@PathVariable String relatingUserID, @PathVariable String relatedUserID){
        friendService.deleteFriend(relatingUserID, relatedUserID);
    }

    @GetMapping("/users/{userid}/friends/request")
    public List<FriendDTO> getFriendRequests(@PathVariable String userid){
        return friendService.getFriendRequests(userid);
    }

    @PostMapping("/users/{relatedUserID}/friends/{relatingUserID}") //수정
    public void acceptFriendRequest(@PathVariable String relatingUserID, @PathVariable String relatedUserID){
        friendService.acceptFriendRequest(relatingUserID, relatedUserID);
    }

    @DeleteMapping("/users/{relatedUserID}/friends/{relatingUserID}")
    public void rejectFriendRequest(@PathVariable String relatingUserID, @PathVariable String relatedUserID){
        friendService.rejectFriendRequest(relatingUserID, relatedUserID);
    }

    @GetMapping("/users/{userid}/friends")
    public List<FriendDTO> getFriendLists(@PathVariable String userid){

        return friendService.getFriendLists(userid);
    }

}
