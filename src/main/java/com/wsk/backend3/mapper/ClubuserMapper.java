package com.wsk.backend3.mapper;

import com.wsk.backend3.domain.Clubuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 魏绍康
* @description 针对表【clubuser(社团人员表)】的数据库操作Mapper
* @createDate 2024-07-17 12:22:55
* @Entity com.wsk.backend3.domain.Clubuser
*/
public interface ClubuserMapper extends BaseMapper<Clubuser> {

    Clubuser findOneByUserIdAndClubId(@Param("userId") int userId, @Param("clubId") int clubId);

    Void insertUserToClub(@Param("clubId") int clubId,@Param("userId") int userId);

}




