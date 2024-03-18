package com.geekcloud.kanborad.dataobject;

import com.geekcloud.kanborad.model.User;

import java.time.LocalDateTime;

public class UserDO {
    private long id;

    private String userName;

    private String pwd;

    private String nickName;

    private String avatar;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * DO 转换为 Model
     *
     * @return User
     */
    public User toModel() {
        User user = new User();
        user.setId(getId());
        user.setUserName(getUserName());
        user.setNickName(getNickName());
        user.setAvatar(getAvatar());
        user.setGmtCreated(getGmtCreated());
        user.setGmtModified(getGmtModified());
        return user;
    }
}
