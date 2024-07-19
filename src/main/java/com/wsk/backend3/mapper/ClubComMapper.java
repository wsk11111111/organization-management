package com.wsk.backend3.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.ClubCom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsk.backend3.dto.ClubComDto;

/**
* @author 魏绍康
* @description 针对表【club_com(社团人员表)】的数据库操作Mapper
* @createDate 2024-07-18 00:50:09
* @Entity com.wsk.backend3.domain.ClubCom
*/
public interface ClubComMapper extends BaseMapper<ClubCom> {

    Page<ClubComDto> selectPageById(Page<Object> objectPage, Integer userId);
}




