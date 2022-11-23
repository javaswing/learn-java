package com.zxd.springbootmybatis.mapper;

import com.zxd.springbootmybatis.domain.UserEntity;
import com.zxd.springbootmybatis.enums.UserSexEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new UserEntity("aa1", "a123456"));
        userMapper.insert(new UserEntity("bb1", "b123456"));
        userMapper.insert(new UserEntity("cc1", "b123456"));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = userMapper.getAll();
        System.out.println(users.toString());
    }


    @Test
    public void testUpdate() throws Exception {
        UserEntity user = userMapper.getOne(30l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
        Assert.assertTrue(("neo".equals(userMapper.getOne(30l).getNickName())));
    }
}
