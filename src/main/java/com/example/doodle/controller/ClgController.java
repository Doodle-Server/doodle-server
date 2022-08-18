package com.example.doodle.controller;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.service.ClgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor

public class ClgController {
    @Autowired
    ClgService clgService;


    @PostMapping("/challenges")
    public String postChallenge(@RequestBody ClgDTO clgDTO){
        clgService.createClg(clgDTO);
//      log.info(clgDTO.getClgid());
        return "redirect:/challenges";

    }

    @DeleteMapping("/challenges/{clgid}")
    public String deleteChallenge(@PathVariable("clgid") String clgid){
        clgService.deleteClg(clgid);
        return "redirect:/challenges";
    }


    @ResponseBody
    @GetMapping("/challenges/{userid}")
    public List<ClgDTO> getDailyChallenges(@PathVariable("userid") String userid){
        LocalDate currentDate = LocalDate.now();
        List<ClgDTO> clgAll = clgService.getClgAll(userid)
                        .stream().filter(e->e.getEnd_date().toInstant().isAfter(Instant.from(currentDate))).collect(Collectors.toList());
//      log.info(String.valueOf(clgAll));
        return clgAll;

    }


}
