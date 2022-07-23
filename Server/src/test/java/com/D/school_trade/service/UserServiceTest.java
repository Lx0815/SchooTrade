package com.D.school_trade.service;

import com.D.school_trade.config.SpringConfig;
import com.D.school_trade.pojo.LoginUser;
import com.D.school_trade.pojo.User;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() {

        Pair<Boolean, User> userPair = userService.login(new LoginUser("7210764207", "123456"));
        System.out.println(userPair);

    }

    @Test
    public void getUserInfo() {

        System.out.println(userService.getUserInfoById(1));

    }
}