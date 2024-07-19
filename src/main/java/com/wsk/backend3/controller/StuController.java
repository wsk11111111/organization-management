package com.wsk.backend3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.common.PlainResult;
import com.wsk.backend3.domain.StarClub;
import com.wsk.backend3.dto.*;
import com.wsk.backend3.service.StuService;
import com.wsk.backend3.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class StuController {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private StuService stuService;
    @GetMapping("/userIf")
    public PlainResult<StuInfoDto> getUserInfo(@RequestParam String token) {
        String username = jwtUtil.extractUsername(token);
        StuInfoDto stuInfoDto = stuService.getUserByUsername(username);
        return PlainResult.success(stuInfoDto);
    }



    @GetMapping("/search")
    public PlainResult<IPage<StuInfoDto>> searchStu(@RequestParam (required = false)Integer pageNum,
                                                    @RequestParam (required = false)Integer pageSize,
                                                    @RequestParam (required = false)String trueName,
                                                    @RequestParam (required = false)String email){
        IPage<StuInfoDto> stuInfoDtoIPage = stuService.searchStu(pageNum,pageSize,trueName,email);

        return PlainResult.success(stuInfoDtoIPage);
    }

    @PutMapping("/{id}")
    public PlainResult<String> updateUser(@PathVariable int id, @RequestBody StuInfoDto userInfoDto) {
        stuService.updateUser(id, userInfoDto);
        return PlainResult.success("success");
    }
    @DeleteMapping("/{id}")
    public PlainResult<String> deleteUser(@PathVariable int id) {
        stuService.deleteUser(id);
        return PlainResult.success("success");
    }
    @PostMapping("/deleteBatch")
    public PlainResult<String> deleteBatch(@RequestBody List<Long> ids) {
        stuService.batchDelete(ids);
        return PlainResult.success("success");
    }
    @PostMapping("/password")
    public PlainResult<String> updateUserPassword(@RequestBody StuPasswordChangeDto stuPasswordChangeDto) {
        stuService.updateStuPassword(stuPasswordChangeDto);
        return PlainResult.success("success");
    }
    @PostMapping("/join")
    public PlainResult<String> applytojoin(@RequestBody ApplytojoinDto applytojoinDto){
//        System.out.println(applytojoinDto);
        int res =stuService.applytojoin(applytojoinDto);
        if(res==1){
            return PlainResult.fail(400,"您已经在社团中");
        }else if(res ==2){
            return PlainResult.fail(401,"您已发送申请");
        }
        return PlainResult.success("success");
    }

    @PostMapping("/star")
    public PlainResult<String> starClub(@RequestBody StarClub starClub){
        int res =stuService.starClub(starClub);
        if(res==1){
            return PlainResult.fail(400,"您已经收藏社团");
        }
        return PlainResult.success("success");
    }


}
