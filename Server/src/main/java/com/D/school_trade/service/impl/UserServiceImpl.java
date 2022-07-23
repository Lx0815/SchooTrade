package com.D.school_trade.service.impl;

import com.D.school_trade.dao.UserDetailMapper;
import com.D.school_trade.dao.UserMapper;
import com.D.school_trade.pojo.LoginUser;
import com.D.school_trade.pojo.User;
import com.D.school_trade.service.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Ding
 * @date: 2022/7/17 6:13
 * @description:
 * @modify:
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public Pair<Boolean, User> login(LoginUser loginUser) {
        Integer userId = userDetailMapper.selectByUsernameAndPassword(loginUser);
        if (userId == null) {
            // 用户不存在
            return new Pair<>(Boolean.FALSE, null);
        } else {
            // 用户存在
            User user = userMapper.selectById(userId);
            return new Pair<>(Boolean.TRUE, user);
        }
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.selectById(id);
    }
}
