package com.wsk.backend3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.domain.Club;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsk.backend3.dto.ClubActDto;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.dto.StuInfoDto;

import java.util.List;

/**
* @author 魏绍康
* @description 针对表【club】的数据库操作Service
* @createDate 2024-07-16 16:39:58
*/
public interface ClubService {

    IPage<ClubInfoDto> searchClub(Integer pageNum, Integer pageSize, String category, String name);

    ClubInfoDto getClubByName(String name);

    void updateClub(int id, ClubInfoDto clubInfoDto);

    void deleteClub(int id);

    IPage<ClubInfoDto> searchMyClub(Integer pageNum, Integer pageSize, Integer userId);

    List<String> searchClubAct(int id);

    void setAct(int id, ClubActDto clubActDto);

    IPage<ClubInfoDto> searchStarClub(Integer pageNum, Integer pageSize, Integer userId);
}
