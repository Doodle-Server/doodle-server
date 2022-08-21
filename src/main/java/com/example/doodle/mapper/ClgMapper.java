package com.example.doodle.mapper;

import com.example.doodle.dto.ClgDTO;
import com.example.doodle.dto.UserDTO;
import com.example.doodle.dto.UserSimpleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface ClgMapper {
    void createClg(ClgDTO clgDTO);

    void includeMember(String userid, String clgid);

    void deleteClg(String clgid);

    ClgDTO getClgById(String clgid);

    List<ClgDTO> findAll(String userid);

    void removeMember(String userid, String clgid);

    List<UserSimpleDTO> getClgMembers(String clgid);


}
