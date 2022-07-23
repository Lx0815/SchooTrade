package com.D.school_trade.dao;

import com.D.school_trade.pojo.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository
@Component
public interface UserDetailMapper {

    Integer selectByUsernameAndPassword(LoginUser loginUser);

}
