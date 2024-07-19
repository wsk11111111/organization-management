package com.wsk.backend3.security;


import com.wsk.backend3.domain.Stu;
import com.wsk.backend3.mapper.StuMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public UserDetails loadUserByUsername(String stuname) throws UsernameNotFoundException {
        com.wsk.backend3.domain.Stu stu=stuMapper.findOneByUsername(stuname);
        if(stu==null){
            throw new UsernameNotFoundException("User not found with username: " + stuname);
        }
        return  new User(stu.getUsername(),stu.getPassword(),new ArrayList<>());

    }
}

