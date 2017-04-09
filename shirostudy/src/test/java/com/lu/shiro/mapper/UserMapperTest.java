package com.lu.shiro.mapper;

import com.lu.shiro.base.SpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/4/4.
 */
public class UserMapperTest extends SpringBootTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testExist() {
        assert userMapper.checkUserExist("root");
    }

    @Test
    public void testNotExist() {
        assert !userMapper.checkUserExist("GG思密达");
    }
}
