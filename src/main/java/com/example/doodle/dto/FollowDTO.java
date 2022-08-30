/*package com.example.doodle.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.NoArgsConstructor;
//import org.apache.catalina.User;

import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class 3FollowDTO {
    @Id
    private long followId;

    @JoinColumn(name = "relatingUserID")
    @ManyToOne
    private String relatingUserID;

    //@JoinColumn(name = "relatedUserID")
    @ManyToOne
    private String relatedUserID;


    @QueryProjection
    public FollowDTO(UserDTO relatingUser, UserDTO relatedUser){
        this.relatingUserID = relatingUser.getUserid();
        this.relatedUserID = relatedUser.getUserid();
    }
}*/
