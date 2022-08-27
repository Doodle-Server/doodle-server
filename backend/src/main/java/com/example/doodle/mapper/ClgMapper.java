package com.example.doodle.mapper;

import com.example.doodle.dto.AcheiveDTO;
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

    void modifyClg(ClgDTO clgDTO);

    ClgDTO getClgById(String clgid);

    String findMemberById(String userid, String clgid);

    List<ClgDTO> findAll(String userid);

    void removeMember(String userid, String clgid);

    List<UserDTO> getClgMembers(String clgid);

    List<AcheiveDTO> getAchieveRate(String cldid);

    List<ClgDTO> getClgByCateId(String clgCateId);

    Float getClgRecord(String clgdid);

    List<HashMap<String, String>> getDailyClg(String userid, Date date);


}
