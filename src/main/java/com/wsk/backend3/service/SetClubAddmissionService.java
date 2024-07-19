package com.wsk.backend3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.domain.SetClubApply;
import com.wsk.backend3.dto.SetClubAddmissionInfoDto;

public interface SetClubAddmissionService {
    IPage<SetClubAddmissionInfoDto> searchSCA(Integer pageNum, Integer pageSize);
}
