package com.example.doodle.mapper;

import com.example.doodle.dto.FriendDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<FriendDTO> getFriendRequests(String userid);

    void requestFriend(@Param("relatingUserID")String relatingUserID, @Param("relatedUserID")String relatedUserID);

    void acceptFriendRequest(@Param("relatingUserID") String relatingUserID, @Param("relatedUserID") String relatedUserID);

    void rejectFriendRequest(@Param("relatingUserID") String relatingUserID,@Param("relatedUserID") String relatedUserID);

    List<FriendDTO> getFriendLists(String userid);

    void deleteFriend(@Param("relatingUserID")String relatingUserID, @Param("relatedUserID")String relatedUserID);

}
