package com.D.school_trade.dao;

import com.D.school_trade.pojo.LoginUser;
import com.D.school_trade.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {



    User selectById(@Param("id") Integer id);
}
