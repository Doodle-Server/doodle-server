package com.example.doodle.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ClgAchieveDTO {
    private String userid;
    private Boolean done;
    private Date clgDate;
}
