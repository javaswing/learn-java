package com.zxd.springjwt.mapper;

import com.zxd.springjwt.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User loadUserByUsername(@Param("username") String username);
}
