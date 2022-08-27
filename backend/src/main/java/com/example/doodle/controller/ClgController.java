package com.example.doodle.controller;

import com.example.doodle.dto.AcheiveDTO;
import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.dto.UserSimpleDTO;
import com.example.doodle.service.ClgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    //챌린지 생성
    @PostMapping("/challenges")
    public void postChallenge(@RequestBody ClgDTO clgDTO){
        clgService.createClg(clgDTO);
//      log.info(clgDTO.getClgid());

    }

    //챌린지 삭제
    @DeleteMapping("/challenges/{clgid}")
    public void deleteChallenge(@PathVariable String clgid){
        clgService.deleteClg(clgid);
    }

    //챌린지 수정
    @PutMapping("challenges/{clgid}")
    public void modifyChallenge(@PathVariable String clgid, @RequestBody ClgDTO clgDTO){
        clgService.modifyChallenge(clgDTO);
    }

    //챌린지 상세내용 조회
    @GetMapping("/challenges/{clgid}")
    public ClgDTO getChallengeInfo(@PathVariable String clgid){
        return clgService.getChallengeInfo(clgid);

    }

    //오늘의 챌린지 조회
    @GetMapping("/challenges/list/{userid}")
    public List<ClgDTO> getDailyChallenges(@PathVariable String userid){
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(String.valueOf(localDate));
        log.info(String.valueOf(clgService.getClgAll(userid)));
        List<ClgDTO> clgAll = clgService.getClgAll(userid).stream().filter(e->e.getEnd_date().after(date)).collect(Collectors.toList());
//      log.info(String.valueOf(clgAll));
        return clgAll;

    }

    //챌린지 참여
    @PostMapping("/challenges/{clgid}/member/{userid}")
    public String joinChallenge(@PathVariable String userid, @PathVariable String clgid){
        clgService.joinChallenge(userid, clgid);
        return "멤버 추가";
    }

    //챌린지 탈퇴
    @DeleteMapping("challenges/{clgid}/member/{userid}")
    public String quitChallenge(@PathVariable String userid,@PathVariable String clgid){
        clgService.quitChallenge(userid, clgid);
        return "멤버 삭제";
    }

    //챌린지 참여 인원 조회
    @GetMapping("challenges/{clgid}/member")
    public List<UserDTO> getClgMembers(@PathVariable String clgid){
        return clgService.getClgMembers(clgid);
    }

//    챌린지별 참여도 순위 조회
    @GetMapping("/challenges/{clgid}/participation")
    public List<AcheiveDTO> getParticipationRank(@PathVariable String clgid){
        return clgService.getParticipationRank(clgid);

    }

    //카테고리 아이디로 챌린지 조회
    @GetMapping("/challenges/category/{clgCateId}")
    public List<ClgDTO> getClgByCateId(@PathVariable String clgCateId){
        return clgService.getClgByCateId(clgCateId);
    }



}
