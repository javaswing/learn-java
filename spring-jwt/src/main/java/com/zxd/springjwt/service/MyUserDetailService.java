package com.zxd.springjwt.service;

import com.zxd.springjwt.domain.Role;
import com.zxd.springjwt.domain.User;
import com.zxd.springjwt.mapper.RoleMapper;
import com.zxd.springjwt.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user != null) {
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        }
        return user;
    }
}
