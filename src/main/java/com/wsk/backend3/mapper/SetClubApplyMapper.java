package com.wsk.backend3.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.SetClubApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsk.backend3.dto.SetClubAddmissionInfoDto;

/**
* @author 魏绍康
* @description 针对表【set_club_apply(社团创建申请表)】的数据库操作Mapper
* @createDate 2024-07-18 15:30:36
* @Entity com.wsk.backend3.domain.SetClubApply
*/
public interface SetClubApplyMapper extends BaseMapper<SetClubApply> {

    Page<SetClubAddmissionInfoDto> selectSCA(Page<Object> objectPage);
}




