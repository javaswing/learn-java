package com.zxd.springbootmybatis.mapper;

import com.zxd.springbootmybatis.enums.UserSexEnum;
import com.zxd.springbootmybatis.domain.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "userName", column = "user_name")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM USERS WHERE ID = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "userName", column = "user_name")
    })
    UserEntity getOne(Long id);


    @Insert("INSERT INTO USERS(user_name, pass_word) values(#{userName}, #{passWord})")
//    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE USERS SET user_name=#{userName}, nick_name=#{nickName} where id =#{id}")
    void update(UserEntity user);

    @Delete("Delete FROM USERS where id =#{id}")
    void delete(Long id);
}
