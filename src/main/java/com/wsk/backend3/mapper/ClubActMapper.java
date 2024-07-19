package com.wsk.backend3.mapper;

import com.wsk.backend3.domain.ClubAct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 魏绍康
* @description 针对表【club_act(社团活动表)】的数据库操作Mapper
* @createDate 2024-07-18 11:39:53
* @Entity com.wsk.backend3.domain.ClubAct
*/
public interface ClubActMapper extends BaseMapper<ClubAct> {

    List<String> searchClubAct(int id);
}




