package com.lu.shiro.mapper;

import com.lu.shiro.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByName(@Param("username") String username);
    User selectByNameAndPassword(@Param("username") String username,@Param("password") String password);
    boolean checkUserExist(@Param("username") String username);
}
