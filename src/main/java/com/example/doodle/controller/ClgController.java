package com.example.doodle.controller;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.service.ClgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class ClgController {
    @Autowired
    ClgService clgService;

    @PostMapping("/challenges")
    public String postChallenge(@ModelAttribute ClgDTO clgDTO){
        clgService.createClg(clgDTO);
        return "redirect:/challenges";

    }

    @DeleteMapping("/challenges")
    public String deleteChallenge(String clgid){
        clgService.deleteClg(clgid);
        return "redirect:/challenges";
    }
}
