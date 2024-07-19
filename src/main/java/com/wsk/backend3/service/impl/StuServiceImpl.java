package com.wsk.backend3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsk.backend3.common.ClubExceptin;
import com.wsk.backend3.domain.Applytojoin;
import com.wsk.backend3.domain.StarClub;
import com.wsk.backend3.domain.Stu;
import com.wsk.backend3.dto.*;
import com.wsk.backend3.mapper.ApplytojoinMapper;
import com.wsk.backend3.mapper.ClubuserMapper;
import com.wsk.backend3.mapper.StarClubMapper;
import com.wsk.backend3.mapper.StuMapper;
import com.wsk.backend3.service.StuService;
import com.wsk.backend3.util.JwtUtil;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Resource
    private ClubuserMapper clubuserMapper;

    @Resource
    private ApplytojoinMapper applytojoinMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;
    //jwt工具类
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private StarClubMapper starClubMapper;

    @Override
    public void register(RegisterDto registerDto) {
        Stu originStu = stuMapper.findOneByUsername(registerDto.getUsername());
        if(originStu != null){
            throw new ClubExceptin(400,"用户名已存在");
        }
        Stu stu = new Stu();
        stu.setStuNum(registerDto.getStuNum());
        stu.setEmail(registerDto.getEmail());
        stu.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        stu.setUsername(registerDto.getUsername());
        stu.setTrueName(registerDto.getTrueName());
        stu.setRole(registerDto.getRole());
        stuMapper.insert(stu);

    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtUtil.generateToken(loginRequestDto.getUsername()));
        return loginResponseDto;
    }

    @Override
    public StuInfoDto getUserByUsername(String username) {
        Stu stu = stuMapper.findOneByUsername(username);
        if(stu == null){
            return null;
        }
        return toStuInfoDto(stu);
    }

    private StuInfoDto toStuInfoDto(Stu stu) {
        StuInfoDto stuInfoDto = new StuInfoDto();
        stuInfoDto.setId(stu.getId());
        stuInfoDto.setTrueName(stu.getTrueName());
        stuInfoDto.setStuNum(stu.getStuNum());
        stuInfoDto.setUsername(stu.getUsername());
        stuInfoDto.setPassword(stu.getPassword());
        stuInfoDto.setEmail(stu.getEmail());
        stuInfoDto.setRole(stu.getRole());
        return stuInfoDto;
    }

    //分页查询学生
    @Override
    public IPage<StuInfoDto> searchStu(Integer pageNum, Integer pageSize, String trueName, String email){
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 1;
        }
        LambdaQueryWrapper<Stu> wrappers = Wrappers.lambdaQuery();
        wrappers.orderByDesc(Stu::getId);

        if (StringUtils.hasText(trueName)) {
            wrappers.like(Stu::getTrueName, trueName);
        }
        if (StringUtils.hasText(email)) {
            wrappers.like(Stu::getEmail, email);
        }
        Page<Stu> stuPage = stuMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        if (stuPage == null) {
            return null;
        }
        return stuPage.convert(this::toStuInfoDto);

    }

    @Override
    public void updateUser(int id, StuInfoDto stuInfoDto) {
        Stu stu = toStu(id , stuInfoDto);

        stuMapper.updateById(stu);
    }

    private Stu toStu(int id, StuInfoDto stuInfoDto) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setRole(stuInfoDto.getRole());
        stu.setStuNum(stuInfoDto.getStuNum());
        stu.setTrueName(stu.getTrueName());
        stu.setUsername(stu.getUsername());
        stu.setEmail(stu.getEmail());
        return stu;
    }

    @Override
    public void deleteUser(int id) {
        stuMapper.deleteById(id);
    }

    @Override
    public void batchDelete(List<Long> ids) {

    }

    @Override
    public void updateStuPassword(StuPasswordChangeDto stuPasswordChangeDto) {
//        if (stuPasswordChangeDto.getId()) {
//            throw new ClubExceptin(400, "用户ID不能为空");
//        }
        if (!StringUtils.hasText(stuPasswordChangeDto.getOldPassword())) {
            throw new ClubExceptin(400, "旧密码不能为空");
        }
        if (!StringUtils.hasText(stuPasswordChangeDto.getNewPassword())) {
            throw new ClubExceptin(400, "新密码不能为空");
        }
        if (Objects.equals(stuPasswordChangeDto.getOldPassword(), stuPasswordChangeDto.getNewPassword())) {
            throw new ClubExceptin(400, "新密码不能与旧密码相同");
        }
        Stu stu = stuMapper.selectById(stuPasswordChangeDto.getId());
        System.out.println(stuPasswordChangeDto);
        if (stu == null) {
            throw new ClubExceptin(400, "用户不存在");
        }
        if (!passwordEncoder.matches(stuPasswordChangeDto.getOldPassword(), stu.getPassword())) {
            throw new ClubExceptin(500, "旧密码错误");
        }
        stu.setPassword(passwordEncoder.encode(stuPasswordChangeDto.getNewPassword()));
        stuMapper.updateById(stu);
    }

    @Override
    public int applytojoin(ApplytojoinDto applytojoinDto) {
        if(clubuserMapper.findOneByUserIdAndClubId(applytojoinDto.getUserId(),applytojoinDto.getClubId())!= null){

//            throw new ClubExceptin(400,"您已经在社团中");
            return 1;
        }
        if (applytojoinMapper.findOneByUserIdAndClubId(applytojoinDto.getUserId(),applytojoinDto.getClubId())!=null){
//            throw new ClubExceptin(400,"您已经发送申请");
            return 2;
        }
        applytojoinMapper.insert(toapplytojoin(applytojoinDto));
        return 3;
    }

    @Override
    public int starClub(StarClub starClub) {

        if(starClubMapper.findOneByUserIdAndClubId(starClub.getUserId(),starClub.getClubId()) !=null){
            return 1;
        }

        StarClub starClub1 = new StarClub();
        starClub1.setClubId(starClub.getClubId());
        starClub1.setUserId(starClub.getUserId());
        starClubMapper.insert(starClub1);
        return 0;

    }

    private Applytojoin toapplytojoin(ApplytojoinDto applytojoinDto) {
        Applytojoin applytojoin = new Applytojoin();
        applytojoin.setUserid(applytojoinDto.getUserId());
        applytojoin.setClubid(applytojoinDto.getClubId());
        applytojoin.setId(applytojoinDto.getId());
        return applytojoin;
    }


}
