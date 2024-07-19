package com.wsk.backend3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.domain.SetClubApply;
import com.wsk.backend3.dto.SetClubAddmissionInfoDto;
import com.wsk.backend3.mapper.ApplytojoinMapper;
import com.wsk.backend3.mapper.SetClubApplyMapper;
import com.wsk.backend3.service.SetClubAddmissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SetClubAddmissionServiceImpl implements SetClubAddmissionService {

    @Resource
    private SetClubApplyMapper setClubApplyMapper;
    @Override
    public IPage<SetClubAddmissionInfoDto> searchSCA(Integer pageNum, Integer pageSize) {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }

        Page<SetClubAddmissionInfoDto> SetClubApplyPage = setClubApplyMapper.selectSCA(new Page<>(pageNum, pageSize));
        return SetClubApplyPage;
    }
}
