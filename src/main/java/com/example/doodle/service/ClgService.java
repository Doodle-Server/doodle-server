package com.example.doodle.service;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.dto.UserSimpleDTO;
import com.example.doodle.mapper.ClgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class ClgService {
    @Autowired
    ClgMapper clgMapper;

    public void createClg(ClgDTO clgDTO){
        clgMapper.createClg(clgDTO);
        String manager = clgDTO.getClgmanagerid();
        String challenge = clgDTO.getClgid();
        clgMapper.includeMember(manager,challenge);
    }

    public void deleteClg(String clgid){
        clgMapper.deleteClg(clgid);
    }

    public String getClgnameById(String clgid){
        ClgDTO clgDTO = clgMapper.getClgById(clgid);
        return  clgDTO.getClgname();
    }

    public ClgDTO getChallengeInfo(String clgid){
        ClgDTO clgDTO = clgMapper.getClgById(clgid);
        log.info(String.valueOf(clgDTO));
        return clgDTO;
    }

    public List<ClgDTO> getClgAll(String userid){
//        log.info(String.valueOf(clgMapper.findAll(userid)));
        return clgMapper.findAll(userid);
    }

    public void joinChallenge(String userid, String clgid){
        clgMapper.includeMember(userid, clgid);
    }

    public void quitChallenge(String userid, String clgid){
        clgMapper.removeMember(userid, clgid);
    }

    public List<UserSimpleDTO> getClgMembers(String clgid){
        return clgMapper.getClgMembers(clgid);
    }

//    public HashMap<String,UserSimpleDTO> getParticipationRank(String clgid){
//        HashMap<String,UserSimpleDTO>
//    }
}
