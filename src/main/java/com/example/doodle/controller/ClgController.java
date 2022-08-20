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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor

public class ClgController {
    @Autowired
    ClgService clgService;


    @PostMapping("/challenges")
    public void postChallenge(@RequestBody ClgDTO clgDTO){
        clgService.createClg(clgDTO);
//      log.info(clgDTO.getClgid());

    }

    @DeleteMapping("/challenges/{clgid}")
    public void deleteChallenge(@PathVariable String clgid){
        clgService.deleteClg(clgid);
    }



    @GetMapping("/challenges/list/{userid}")
    public List<ClgDTO> getDailyChallenges(@PathVariable String userid){
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(String.valueOf(localDate));
        log.info(String.valueOf(clgService.getClgAll(userid)));
        List<ClgDTO> clgAll = clgService.getClgAll(userid).stream().filter(e->e.getEnd_date().after(date)).collect(Collectors.toList());
//      log.info(String.valueOf(clgAll));
        return clgAll;

    }


}
