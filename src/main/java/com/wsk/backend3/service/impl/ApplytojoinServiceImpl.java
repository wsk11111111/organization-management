package com.wsk.backend3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.SetClubApply;
import com.wsk.backend3.dto.ClubInfoDto;
import com.wsk.backend3.mapper.ApplytojoinMapper;
import com.wsk.backend3.mapper.SetClubApplyMapper;
import com.wsk.backend3.service.ApplytojoinService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service

public class ApplytojoinServiceImpl implements ApplytojoinService {

    @Resource
    private ApplytojoinMapper applytojoinMapper;

    @Resource
    private SetClubApplyMapper setClubApplyMapper;
    @Override
    public IPage<ClubInfoDto> searchClub(Integer pageNum, Integer pageSize, Integer userId) {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }
        Page<Club> clubPage = applytojoinMapper.selectPageById(new Page<>(pageNum, pageSize), userId);
        if (clubPage == null) {
            return null;
        }
        return clubPage.convert(this::toClubInfoDto);
    }

    @Override
    public void deleteApply(int id) {
        applytojoinMapper.deleteById(id);
    }

    @Override
    public void applyToSet(String category, String name, Integer userId) {
        SetClubApply setClubApply = new SetClubApply();
        setClubApply.setCategory(category);
        setClubApply.setName(name);
        setClubApply.setUserid(userId);
        setClubApplyMapper.insert(setClubApply);

    }

    private ClubInfoDto toClubInfoDto(Club club) {
        ClubInfoDto clubInfoDto = new ClubInfoDto();
        clubInfoDto.setId(club.getId());
        clubInfoDto.setCategory(club.getCategory());
        clubInfoDto.setName(club.getName());
        clubInfoDto.setComId(club.getComId());
        return clubInfoDto;
    }
}
