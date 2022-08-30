package com.example.doodle.mapper;

import com.example.doodle.dto.FriendDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<FriendDTO> getFriendRequests(String userid);

    void requestFriend(String relatingUserID, String relatedUserID, int type);

    void acceptFriendRequest(String relatingUserID, String relatedUserID);

    void rejectFriendRequest(String relatingUserID, String relatedUserID);

    List<FriendDTO> getFriendLists(String userid);

    void deleteFriend(String relatingUserID, String relatedUserID);

}
