package com.D.school_trade.service;

import com.D.school_trade.pojo.LoginUser;
import com.D.school_trade.pojo.User;
import javafx.util.Pair;


public interface UserService {


    /**
     * 处理用户的登录业务
     * @param loginUser 登录用户的信息
     * @return 返回用户 id ，若 id 为 -1 则登录失败
     */
    Pair<Boolean, User> login(LoginUser loginUser);

    User getUserInfoById(Integer id);
}
