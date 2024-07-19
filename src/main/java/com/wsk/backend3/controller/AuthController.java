package com.wsk.backend3.controller;

import com.wsk.backend3.common.PlainResult;
import com.wsk.backend3.dto.LoginRequestDto;
import com.wsk.backend3.dto.LoginResponseDto;
import com.wsk.backend3.dto.RegisterDto;
import com.wsk.backend3.service.StuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private StuService stuService;

    @PostMapping("/register")
    public PlainResult<String> register(@RequestBody RegisterDto registerDto) {
        stuService.register(registerDto);
        return PlainResult.success("注册成功");
    }

    @PostMapping("/login")
    public PlainResult<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = stuService.login(loginRequestDto);
        return PlainResult.success(loginResponseDto);
    }
}
