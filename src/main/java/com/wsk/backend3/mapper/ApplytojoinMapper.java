package com.wsk.backend3.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.Applytojoin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.Clubuser;
import com.wsk.backend3.dto.ApplytojoinDto;
import org.apache.ibatis.annotations.Param;

/**
* @author 魏绍康
* @description 针对表【applytojoin(学生申请表)】的数据库操作Mapper
* @createDate 2024-07-17 12:22:55
* @Entity com.wsk.backend3.domain.Applytojoin
*/
public interface ApplytojoinMapper extends BaseMapper<Applytojoin> {

    Applytojoin findOneByUserIdAndClubId(@Param("userId")int userId, @Param("clubId")int clubId);

    Page<Club> selectPageById(Page<Object> objectPage,@Param("userId") Integer userId);

}




