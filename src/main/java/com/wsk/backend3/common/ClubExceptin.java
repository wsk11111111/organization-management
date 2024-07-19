package com.wsk.backend3.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClubExceptin extends RuntimeException {

    private Integer code;

    public ClubExceptin(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
