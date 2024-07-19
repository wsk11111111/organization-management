package com.wsk.backend3.dto;

import lombok.Data;

@Data
public class StuPasswordChangeDto {
    private int id;
    private String oldPassword;
    private String newPassword;
}
