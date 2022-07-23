package com.D.school_trade.pojo;

import java.time.LocalDateTime;

/**
 * @author: Ding
 * @date: 2022/7/17 10:50
 * @description: user 表
 * @modify:
 */

public class User {

    /**
     * pk_id，由 java 工具类 UUID 生成
     */
    private Integer id;

    /**
     * headshot
     * 头像
     */
    private String avatar;

    /**
     * nick_name
     * 昵称
     */
    private String nickName;

    /**
     * gender
     * 性别; 1 : 男; 0 : 女
     */
    private Integer gender;

    /**
     * role_id
     * 指向 enums 表中 pk_id 在 20 - 29 范围的数据
     * 其中 20 为普通用户，21为管理员用户
     */
    private Integer roleId;

    /**
     * create_time
     * 账号创建时间
     */
    private LocalDateTime createTime;

    /**
     * update_time
     * 上次修改时间
     */
    private LocalDateTime updateTime;

    public User() {
    }

    public User(Integer id, String avatar, String nickName, int gender, int roleId, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.avatar = avatar;
        this.nickName = nickName;
        this.gender = gender;
        this.roleId = roleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
