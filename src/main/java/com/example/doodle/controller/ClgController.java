package com.example.doodle.controller;

import com.example.doodle.dto.*;
import com.example.doodle.exception.ApiRequestException;
import com.example.doodle.service.ClgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
        if(clgService.getChallengeInfo(clgDTO.getClgid())!=null){
            throw new ApiRequestException("이미 존재하는 챌린지 아이디입니다.");
        }
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
    @GetMapping("/challenges/today/{userid}")
    public List<ClgDTO> getTodayChallenges(@PathVariable String userid){
        if(clgService.getClgnameById(userid).length()==0){
            throw new ApiRequestException("존재하지 않는 아이디입니다.");
        }
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(String.valueOf(localDate));
        List<ClgDTO> clgAll = clgService.getClgAll(userid);
        return clgService.getDailyChallenges(clgAll,date);

    }

    //날짜별 챌린지 조회
    @GetMapping("/challenges/date/{userid}")
    public List<ClgDTO> getDailyChallenge(@PathVariable String userid, @RequestParam("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<ClgDTO> clgAll = clgService.getClgAll(userid);
        return clgService.getDailyChallenges(clgAll,date);

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

    //일별 유저별 달성여부 조회
    @GetMapping("/challenges/{clgid}/record")
    public List<ClgAchieveDTO> getClgAchieve(@PathVariable String clgid){
        return clgService.getClgAchieve(clgid);
    }

    //챌린지 색상 변경
    @PatchMapping("/challenges/{clgid}/color")
    public void changeClgColor(@PathVariable String clgid,  @RequestParam String userid, @RequestParam String color){
        clgService.changeClgColor(clgid, userid, color);
    }

}
