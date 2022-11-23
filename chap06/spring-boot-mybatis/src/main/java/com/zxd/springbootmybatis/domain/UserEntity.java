package com.zxd.springbootmybatis.domain;

import com.zxd.springbootmybatis.enums.UserSexEnum;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String passWord;
    private String nickName;
    private UserSexEnum userSex;

    public UserEntity(String userName, String passWord) {
        super();
        this.passWord = passWord;
        this.userName = userName;
//        this.userSex = userSex;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "userName " + this.userName + ", password " + this.passWord + "sex " + userSex.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }
}
