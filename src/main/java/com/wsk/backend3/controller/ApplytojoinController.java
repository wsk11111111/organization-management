package com.wsk.backend3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.common.PlainResult;
import com.wsk.backend3.domain.Clubuser;
import com.wsk.backend3.dto.ClubComDto;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.dto.StuInfoDto;
import com.wsk.backend3.service.ApplytojoinService;
import com.wsk.backend3.service.ClubComService;
import com.wsk.backend3.service.ClubuserService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atj")
public class ApplytojoinController {
    @Resource
    private ApplytojoinService applytojoinService;
    @Resource
    private ClubuserService clubuserService;
    @Resource
    private ClubComService clubComService;
    @GetMapping("/search")
    public PlainResult<IPage<ClubInfoDto>> searchClub(@RequestParam(required = false)Integer pageNum,
                                                      @RequestParam (required = false)Integer pageSize,
                                                      @RequestParam (required = false)Integer userId){
        IPage<ClubInfoDto> clubInfoDtoIPage = applytojoinService.searchClub(pageNum,pageSize,userId);
        return PlainResult.success(clubInfoDtoIPage);
    }
    @GetMapping("/searchClubCom")
    public PlainResult<IPage<ClubComDto>> searchClubCom(@RequestParam(required = false)Integer pageNum,
                                                        @RequestParam (required = false)Integer pageSize,
                                                        @RequestParam (required = false)Integer userId){
        IPage<ClubComDto> ClubComDtoIPage = clubComService.searchClubCom(pageNum,pageSize,userId);
        return PlainResult.success(ClubComDtoIPage);
    }
    @GetMapping("/{id}")
    public PlainResult<String> approveJoin(@PathVariable int id){
        clubuserService.insertUser(id);
        applytojoinService.deleteApply(id);
        return PlainResult.success("success");
    }
    @DeleteMapping("/{id}")
    public PlainResult<String> disApproveJoin(@PathVariable int id){

        applytojoinService.deleteApply(id);
        return PlainResult.success("success");
    }

    @GetMapping("/applyToSet")
    public PlainResult<String> applyToSet(@RequestParam String category,
                                          @RequestParam String name,
                                          @RequestParam Integer userId){
        applytojoinService.applyToSet(category,name,userId);
        return PlainResult.success("success");
    }

}
