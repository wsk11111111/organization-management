package com.wsk.backend3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.common.PlainResult;
import com.wsk.backend3.domain.SetClubApply;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.dto.SetClubAddmissionInfoDto;
import com.wsk.backend3.service.SetClubAddmissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sca")

public class SetClubAddmissionController {
    @Resource
    private SetClubAddmissionService setClubAddmissionService;

    @GetMapping("/search")
    public PlainResult <IPage<SetClubAddmissionInfoDto>> searchSCA(@RequestParam(required = false)Integer pageNum,
                                                       @RequestParam (required = false)Integer pageSize){
        IPage<SetClubAddmissionInfoDto> SetClubAddmissionInfoDtoIPage = setClubAddmissionService.searchSCA(pageNum,pageSize);
        return PlainResult.success(SetClubAddmissionInfoDtoIPage);
    }

}
