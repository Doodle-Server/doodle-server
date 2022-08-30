package com.example.doodle.service;

import com.example.doodle.dto.FriendDTO;
import com.example.doodle.mapper.FriendMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FriendService {
    @Autowired
    FriendMapper friendMapper;


    public void requestFriend(String relatingUserID, String relatedUserID) {
        friendMapper.requestFriend(relatingUserID, relatedUserID);
    }

    public void deleteFriend(String relatingUserID, String relatedUserID) {
        friendMapper.deleteFriend(relatingUserID, relatedUserID);
    }

    public List<FriendDTO> getFriendRequests(String userid){
        return friendMapper.getFriendRequests(userid);
    }

    public void acceptFriendRequest(String relatingUserID, String relatedUserID){
        friendMapper.acceptFriendRequest(relatingUserID, relatedUserID);
    }

    public void rejectFriendRequest(String relatingUserID, String relatedUserID){
        friendMapper.rejectFriendRequest(relatingUserID, relatedUserID);
    }

    public List<FriendDTO> getFriendLists(String userid) {
        return friendMapper.getFriendLists(userid);
    }

}
