package com.D.school_trade.pojo;

import java.time.LocalDateTime;
import java.time.Year;

/**
 * @author: Ding
 * @date: 2022/7/16
 * @description: user_detail 表
 * @modify:
 */
public class UserDetail {

    /**
     * pk_id
     * 主键 id，和 {@link User#getId()} 完全相同
     */
    private Integer id;

    /**
     * school_card_id
     * 校园卡 id，即登录的账号
     */
    private String schoolCardId;

    /**
     * real_name
     * 真实姓名
     */
    private String realName;

    /**
     * password
     * 密码，采用 sha256 + MD5 加密
     */
    private String password;

    /**
     * status
     * 指向 enums 表中 pk_id 在 10 - 19 范围的数据
     * 其中 10 表示正常，11表示已封禁。若要扩充，还有12 - 19的范围可以使用
     */
    private int status;

    /**
     * grade
     * 所在年级，单位为 级
     */
    private Year grade;

    /**
     * major_name
     * 专业名称
     */
    private String majorName;

    /**
     * class_num
     * 班级序号
     */
    private String classNum;

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

    public UserDetail() {
    }

    public UserDetail(Integer id, String schoolCardId, String realName, String password, int status, Year grade, String majorName, String classNum, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.schoolCardId = schoolCardId;
        this.realName = realName;
        this.password = password;
        this.status = status;
        this.grade = grade;
        this.majorName = majorName;
        this.classNum = classNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolCardId() {
        return schoolCardId;
    }

    public void setSchoolCardId(String schoolCardId) {
        this.schoolCardId = schoolCardId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Year getGrade() {
        return grade;
    }

    public void setGrade(Year grade) {
        this.grade = grade;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
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
        return "UserDetail{" +
                "id=" + id +
                ", schoolCardId='" + schoolCardId + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", grade=" + grade +
                ", majorName='" + majorName + '\'' +
                ", classNum='" + classNum + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
