package com.wsk.backend3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.domain.StarClub;
import com.wsk.backend3.dto.*;

import java.util.List;

public interface StuService {
    /**
     * 用户注册
     * @param registerDto
     */
    void register(RegisterDto registerDto);


    /**
     * 用户登录
     * @param loginRequestDto
     * @return
     */
    LoginResponseDto login(LoginRequestDto loginRequestDto);


    StuInfoDto getUserByUsername(String username);

    IPage<StuInfoDto> searchStu(Integer pageNum, Integer pageSize, String trueName, String email);

    void updateUser(int id, StuInfoDto userInfoDto);

    void deleteUser(int id);

    void batchDelete(List<Long> ids);

    void updateStuPassword(StuPasswordChangeDto stuPasswordChangeDto);

    int applytojoin(ApplytojoinDto applytojoinDto);

    int starClub(StarClub starClub);
}
