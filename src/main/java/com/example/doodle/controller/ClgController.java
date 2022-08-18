package com.example.doodle.controller;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.service.ClgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor

public class ClgController {
    @Autowired
    ClgService clgService;

    @PostMapping("/challenges")
    public String postChallenge(@RequestBody ClgDTO clgDTO){
        clgService.createClg(clgDTO);
//        log.info(clgDTO.getClgid());
        return "challenges";

    }

    @DeleteMapping("/challenges/{clgid}")
    public String deleteChallenge(String clgid){
        clgService.deleteClg(clgid);
        List<UserDTO> list = clgService.findAll();
//        log.debug(String.valueOf(list));
        return "redirect:/challenges";
    }


    @GetMapping("/challenges")
    public ArrayList<UserDTO> getchallenges(){
        ArrayList<UserDTO> clgAll = clgService.findAll();
        log.info(String.valueOf(clgAll));
        return clgAll;

    }


}
