package com.zxd.springjwt.service;

import com.zxd.springjwt.domain.Role;
import com.zxd.springjwt.domain.User;
import com.zxd.springjwt.mapper.RoleMapper;
import com.zxd.springjwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userMapper.loadUserByUsername(username);
        if(user != null) {
            List<Role> list = roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(list);
        }
        return user;
    }
}
