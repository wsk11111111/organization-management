package com.wsk.backend3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.ClubCom;
import com.wsk.backend3.dto.ClubComDto;
import com.wsk.backend3.service.ClubComService;
import com.wsk.backend3.mapper.ClubComMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author 魏绍康
* @description 针对表【club_com(社团人员表)】的数据库操作Service实现
* @createDate 2024-07-18 00:50:09
*/
@Service
public class ClubComServiceImpl implements ClubComService{

    @Resource
    private ClubComMapper clubComMapper;
    @Override
    public IPage<ClubComDto> searchClubCom(Integer pageNum, Integer pageSize, Integer userId) {

        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }
        Page<ClubComDto> clubComPage = clubComMapper.selectPageById(new Page<>(pageNum, pageSize), userId);
        if (clubComPage == null) {
            return null;
        }
        System.out.println(clubComPage.getRecords());
        return clubComPage;
    }
}




