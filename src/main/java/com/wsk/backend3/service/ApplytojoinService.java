package com.wsk.backend3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsk.backend3.dto.ClubInfoDto;

public interface ApplytojoinService {
    IPage<ClubInfoDto> searchClub(Integer pageNum, Integer pageSize, Integer userId);

    void deleteApply(int id);

    void applyToSet(String category, String name, Integer userId);
}
