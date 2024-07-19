package com.wsk.backend3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.domain.ClubCom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsk.backend3.dto.ClubComDto;

/**
* @author 魏绍康
* @description 针对表【club_com(社团人员表)】的数据库操作Service
* @createDate 2024-07-18 00:50:09
*/
public interface ClubComService  {

    IPage<ClubComDto> searchClubCom(Integer pageNum, Integer pageSize, Integer userId);
}
