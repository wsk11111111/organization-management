package com.wsk.backend3.dto;

import lombok.Data;

@Data
public class StuInfoDto {
    private int id;
    private String trueName;
    private String stuNum;
    private String username;
    private String password;
    private String email;
    private int role;

}
