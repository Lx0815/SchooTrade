package com.D.school_trade.web.controller;

import com.D.school_trade.common.utils.JWTUtils;
import com.D.school_trade.pojo.LoginUser;
import com.D.school_trade.pojo.User;
import com.D.school_trade.service.UserService;
import com.D.school_trade.web.response_obj.ResponseObj;
import com.D.school_trade.web.response_obj.ResponseResultData;
import javafx.util.Pair;
import logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.D.school_trade.web.response_obj.ResponseCode.*;

/**
 * @author: Ding
 * @date: 2022/7/16
 * @description:
 * @modify:
 */


// 配置跨域请求
@CrossOrigin(origins = {"http://localhost:9528", "http://192.168.0.102:9528/"},
        methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS},
        // 允许对跨域请求发送 token
        allowCredentials = "true")
@RestController
public class UserController {


    private static final String DEFAULT_USER_ID = "-1";

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Object login(@RequestBody LoginUser loginUser) {
        if (loginUser == null)
            return new ResponseObj(FAIL, "服务器未接收到参数", new ResponseResultData(null, null));

        LoggerUtils.logInfo("收到 " + loginUser.getSchoolCardId() + " 登录请求，密码为：" + loginUser.getPassword());


        Pair<Boolean, User> result = userService.login(loginUser);
        if (result.getKey()) {
            // 登录成功
            String token = JWTUtils.sign(result.getValue().getId(), loginUser.getSchoolCardId(), loginUser.getPassword());
            LoggerUtils.logInfo("用户 " + loginUser.getSchoolCardId() + " 生成的 token 为：" + token);

            return new ResponseObj(SUCCESS, loginUser.getSchoolCardId() + "登录成功",
                    new ResponseResultData(result.getValue(), token));
        } else {
            return new ResponseObj(FAIL, "用户名或密码错误",
                    new ResponseResultData(null, null));
        }
    }


    @GetMapping("/user/info/{id}")
    public Object getUserInfo(@PathVariable String id, @RequestHeader(name = "X-Token") String token) {
        LoggerUtils.logInfo("id : " + id);

        if (id == null) {
            return new ResponseObj(FAIL, "未接受到参数", new ResponseResultData(null, null));
        }

        if (DEFAULT_USER_ID.equals(id)) {
            // 尝试从 token 中获取参数
            Integer id0 = JWTUtils.getId(token);
            LoggerUtils.logInfo("从 token 取出 id : " + id0);
            if (id0 != null) {
                id = String.valueOf(id0);
            }
        }
        if (!id.matches("^\\d+$")) {
            return new ResponseObj(FAIL, "参数格式错误", new ResponseResultData(null, null));
        }
        // 获取信息
        User user = userService.getUserInfoById(Integer.parseInt(id));

        return new ResponseObj(SUCCESS, "",
                new ResponseResultData(user , ""));
    }
}
