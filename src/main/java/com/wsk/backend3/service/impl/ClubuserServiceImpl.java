package com.wsk.backend3.service.impl;

import com.wsk.backend3.domain.Applytojoin;
import com.wsk.backend3.domain.Club;
import com.wsk.backend3.domain.Clubuser;
import com.wsk.backend3.dto.ApplytojoinDto;
import com.wsk.backend3.mapper.ApplytojoinMapper;
import com.wsk.backend3.mapper.ClubuserMapper;
import com.wsk.backend3.service.ClubuserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClubuserServiceImpl implements ClubuserService {
    @Resource
    private ApplytojoinMapper applytojoinMapper;

    @Resource
    private ClubuserMapper clubuserMapper;
    @Override
    public void insertUser(int id) {
        Applytojoin applytojoin = new Applytojoin();
        applytojoin = applytojoinMapper.selectById(id);
        Clubuser clubuser = new Clubuser();
        clubuser.setUserid(applytojoin.getUserid());
        clubuser.setClubid(applytojoin.getClubid());
        clubuserMapper.insert(clubuser);
    }
}
