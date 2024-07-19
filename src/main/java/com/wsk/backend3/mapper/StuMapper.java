package com.wsk.backend3.mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.dto.StuInfoDto;
import org.apache.ibatis.annotations.Param;

import com.wsk.backend3.domain.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 魏绍康
* @description 针对表【stu】的数据库操作Mapper
* @createDate 2024-07-15 21:17:47
* @Entity com.wsk.backend3.domain.Stu
*/

public interface StuMapper extends BaseMapper<Stu> {

    Stu findOneByUsername(@Param("username") String username);

}




