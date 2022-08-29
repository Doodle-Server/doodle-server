package com.example.doodle.mapper;

import com.example.doodle.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface ClgMapper {
    void createClg(ClgDTO clgDTO);

    void includeMember(String userid, String clgid);

    void deleteClg(String clgid);

    void deleteMemberInClg(String clgid);

    void modifyClg(ClgDTO clgDTO);

    ClgDTO getClgById(String clgid);

    String getManagerId(String clgid);
    String findMemberById(String userid, String clgid);

    List<ClgDTO> findAll(String userid);

    void removeMember(String userid, String clgid);

    List<UserDTO> getClgMembers(String clgid);

    List<AcheiveDTO> getAchieveRate(String cldid);

    List<ClgDTO> getClgByCateId(String clgCateId);

    List<ClgAchieveDTO> getClgAchieve(String clgid);

    void changeClgColor(String clgid, String userid, String color);


}
