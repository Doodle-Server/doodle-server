package com.example.doodle.mapper;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClgMapper {
    void createClg(ClgDTO clgDTO);

    void includeManager(String userid, String clgid);

    void deleteClg(String clgid);

    ClgDTO getClgById(String clgid);

    List<ClgDTO> findAll(String userid);


}
