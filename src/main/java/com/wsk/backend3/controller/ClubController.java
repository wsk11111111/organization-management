package com.wsk.backend3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.common.PlainResult;
import com.wsk.backend3.dto.ClubActDto;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.dto.StuInfoDto;
import com.wsk.backend3.service.ClubService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/search")
    public PlainResult<IPage<ClubInfoDto>> searchClub(@RequestParam(required = false)Integer pageNum,
                                                      @RequestParam (required = false)Integer pageSize,
                                                      @RequestParam (required = false)String category,
                                                      @RequestParam (required = false)String name){
        IPage<ClubInfoDto> clubInfoDtoIPage = clubService.searchClub(pageNum,pageSize,category,name);
        return PlainResult.success(clubInfoDtoIPage);
    }
    @GetMapping("/searchMine")

    public PlainResult<IPage<ClubInfoDto>> searchMyClub(@RequestParam(required = false)Integer pageNum,
                                                      @RequestParam (required = false)Integer pageSize,
                                                      @RequestParam (required = false)Integer userId){
        IPage<ClubInfoDto> clubInfoDtoIPage = clubService.searchMyClub(pageNum,pageSize,userId);
        return PlainResult.success(clubInfoDtoIPage);
    }

    @PostMapping("/{id}")
    public PlainResult<String> updateClub(@PathVariable int id, @RequestBody ClubInfoDto clubInfoDto) {
        clubService.updateClub(id, clubInfoDto);
        return PlainResult.success("success");
    }
    @DeleteMapping("/{id}")
    public PlainResult<String> deleteClub(@PathVariable int id) {
        clubService.deleteClub(id);
        return PlainResult.success("success");
    }
    @GetMapping("/getClubAct/{id}")
    public PlainResult<List<String>> searchClubAct(@PathVariable int id){
        return PlainResult.success(clubService.searchClubAct(id));

    }
    @PutMapping("/setAct/{id}")
    public PlainResult<String> updateUser(@PathVariable int id, @RequestBody ClubActDto clubActDto) {
        clubService.setAct(id, clubActDto);
        return PlainResult.success("success");
    }
    @GetMapping("/starSearch")
    public PlainResult<IPage<ClubInfoDto>> searchClubCom(@RequestParam(required = false)Integer pageNum,
                                                         @RequestParam (required = false)Integer pageSize,
                                                         @RequestParam (required = false)Integer userId){
        IPage<ClubInfoDto> ClubInfoDtoIPage = clubService.searchStarClub(pageNum,pageSize,userId);
        return PlainResult.success(ClubInfoDtoIPage);
    }
}
