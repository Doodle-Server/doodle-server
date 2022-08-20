package com.example.doodle.service;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.mapper.ClgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ClgService {
    @Autowired
    ClgMapper clgMapper;

    public void createClg(ClgDTO clgDTO){
        clgMapper.createClg(clgDTO);
        String manager = clgDTO.getClgmanagerid();
        String challenge = clgDTO.getClgid();
        clgMapper.includeManager(manager,challenge);
    }

    public void deleteClg(String clgid){
        clgMapper.deleteClg(clgid);
    }

    public String getClgnameById(String clgid){
        ClgDTO clgDTO = clgMapper.getClgById(clgid);
        return  clgDTO.getClgname();
    }

    public List<ClgDTO> getClgAll(String userid){
//        log.info(String.valueOf(clgMapper.findAll(userid)));
        return clgMapper.findAll(userid);
    }
}
