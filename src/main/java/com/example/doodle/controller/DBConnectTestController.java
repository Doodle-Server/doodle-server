package com.example.doodle.controller;

import com.example.doodle.dao.TestDAO;
import com.example.doodle.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class DBConnectTestController {
    @Autowired
    private TestDAO testDAO;

    @GetMapping("/hello")
    public List<TestDTO> HelloWorld(){
        return testDAO.getTestData();
    }

}
