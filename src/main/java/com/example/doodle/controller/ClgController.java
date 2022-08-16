package com.example.doodle.controller;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.service.ClgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor

public class ClgController {
    @Autowired
    ClgService clgService;

    @PostMapping("/challenges")
    public String postChallenge(@ModelAttribute ClgDTO clgDTO){
        clgService.createClg(clgDTO);
        return "challenges";

    }

    @DeleteMapping("/challenges")
    public String deleteChallenge(String clgid){
        clgService.deleteClg(clgid);
        List<Map<String,Object>> list = clgService.findAll();
//        log.info(list);
        return "redirect:/challenges";
    }


//    @GetMapping("/challenges")
//    public List<Map<String, Object>> getchallenges(){
//        List<Map<String, Object>> Challenges = clgService.findAll();
//        return "redirect:/challenges";
//
//    }


}
