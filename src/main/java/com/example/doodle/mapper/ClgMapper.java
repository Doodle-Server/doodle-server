package com.example.doodle.mapper;

import com.example.doodle.dto.ClgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClgMapper {
    void createClg(ClgDTO clgDTO);

    void deleteClg(String clgid);

    ClgDTO getClgById(String clgid);

    List<Map<String, Object>> findAll();


}
