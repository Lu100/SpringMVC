package com.lu.shiro;

import com.lu.shiro.configure.MyConfigure;
import com.lu.shiro.mapper.UserMapper;
import com.lu.shiro.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyConfigure.class)
public class AbstractTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void dataSourceTest() throws SQLException {
        User root = userMapper.selectByName("root");
        System.out.println(root);
    }

}
