package com.wsk.backend3.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import com.wsk.backend3.domain.Club;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 魏绍康
* @description 针对表【club】的数据库操作Mapper
* @createDate 2024-07-16 17:29:05
* @Entity com.wsk.backend3.domain.Club
*/
public interface ClubMapper extends BaseMapper<Club> {
    Club findOneByName(@Param("name") String name);

    Page<Club> selectPageByUserId(Page<Object> objectPage,@Param("userId") Integer userId);
}




