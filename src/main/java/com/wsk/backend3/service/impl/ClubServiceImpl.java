package com.wsk.backend3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.ClubAct;
import com.wsk.backend3.domain.Stu;
import com.wsk.backend3.dto.ClubActDto;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.dto.StuInfoDto;
import com.wsk.backend3.mapper.ClubActMapper;
import com.wsk.backend3.mapper.StarClubMapper;
import com.wsk.backend3.service.ClubService;
import com.wsk.backend3.mapper.ClubMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 魏绍康
* @description 针对表【club】的数据库操作Service实现
* @createDate 2024-07-16 16:39:58
*/
@Service
public class ClubServiceImpl implements ClubService{

    @Resource
    private ClubMapper clubMapper;


    @Resource
    private ClubActMapper clubActMapper;

    @Resource
    private StarClubMapper starClubMapper;
    private ClubInfoDto toClubInfoDto(Club club) {
        ClubInfoDto clubInfoDto = new ClubInfoDto();
        clubInfoDto.setId(club.getId());
        clubInfoDto.setCategory(club.getCategory());
        clubInfoDto.setName(club.getName());
        clubInfoDto.setComId(club.getComId());
        return clubInfoDto;
    }
    @Override
    public IPage<ClubInfoDto> searchClub(Integer pageNum, Integer pageSize, String category, String name) {

        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }

        LambdaQueryWrapper<Club> wrappers = Wrappers.lambdaQuery();
        wrappers.orderByDesc(Club::getId);
        if (StringUtils.hasText(name)) {
            wrappers.like(Club::getName, name);
        }
        if (StringUtils.hasText(category)) {
            wrappers.like(Club::getCategory, category);
        }
        Page<Club> clubPage = clubMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        if (clubPage == null) {
            return null;
        }

        return clubPage.convert(this::toClubInfoDto);


    }

    @Override
    public ClubInfoDto getClubByName(String name) {

        Club club = clubMapper.findOneByName(name);
        if(club == null){
            return null;
        }
        return toClubInfoDto(club);
    }

    @Override
    public void updateClub(int id, ClubInfoDto clubInfoDto) {
        Club club = toClub(id,clubInfoDto);
        clubMapper.updateById(club);
    }

    private Club toClub(int id, ClubInfoDto clubInfoDto) {
        Club club = new Club();
        club.setId(clubInfoDto.getId());
        club.setCategory(clubInfoDto.getCategory());
        club.setName(clubInfoDto.getName());
        club.setComId(clubInfoDto.getComId());
        return club;
    }

    @Override
    public void deleteClub(int id) {
        clubMapper.deleteById(id);
    }

    @Override
    public IPage<ClubInfoDto> searchMyClub(Integer pageNum, Integer pageSize, Integer userId) {

        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }


        Page<Club> clubPage = clubMapper.selectPageByUserId(new Page<>(pageNum, pageSize), userId);
        if (clubPage == null) {
            return null;
        }

        return clubPage.convert(this::toClubInfoDto);
    }

    @Override
    public List<String> searchClubAct(int id) {

        return clubActMapper.searchClubAct(id);
    }

    @Override
    public void setAct(int id, ClubActDto clubActDto) {
        ClubAct clubAct = new ClubAct();
        clubAct.setClubId(id);
        clubAct.setAct(clubActDto.getAct());
        clubActMapper.insert(clubAct);
    }

    @Override
    public IPage<ClubInfoDto> searchStarClub(Integer pageNum, Integer pageSize, Integer userId) {

        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }


        Page<Club> clubPage = starClubMapper.selectPageByUserId(new Page<>(pageNum, pageSize), userId);
        if (clubPage == null) {
            return null;
        }

        return clubPage.convert(this::toClubInfoDto);
    }

}




