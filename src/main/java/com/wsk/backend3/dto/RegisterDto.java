package com.wsk.backend3.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String trueName;
    private String email;
    private String stuNum;
    private int role;
}
