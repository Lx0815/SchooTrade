package com.D.school_trade.pojo;

/**
 * @author: Ding
 * @date: 2022/7/17 10:58
 * @description:
 * @modify:
 */

public class LoginUser {

    /**
     * school_card_id
     * 校园卡 id，即登录的账号
     */
    private String schoolCardId;

    /**
     * password
     * 密码，采用 sha256 + MD5 加密
     */
    private String password;

    public LoginUser() {
    }

    public LoginUser(String schoolCardId, String password) {
        this.schoolCardId = schoolCardId;
        this.password = password;
    }

    public String getSchoolCardId() {
        return schoolCardId;
    }

    public void setSchoolCardId(String schoolCardId) {
        this.schoolCardId = schoolCardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "schoolCardId='" + schoolCardId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
