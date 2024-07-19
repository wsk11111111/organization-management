package com.wsk.backend3.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.StarClub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 魏绍康
* @description 针对表【star_club(社团收藏表)】的数据库操作Mapper
* @createDate 2024-07-18 13:57:48
* @Entity com.wsk.backend3.domain.StarClub
*/
public interface StarClubMapper extends BaseMapper<StarClub> {

    StarClub findOneByUserIdAndClubId(@Param("userId") Integer userId,@Param("clubId") Integer clubId);

    Page<Club> selectPageByUserId(Page<Object> objectPage, @Param("userIdd") Integer userId);
}




