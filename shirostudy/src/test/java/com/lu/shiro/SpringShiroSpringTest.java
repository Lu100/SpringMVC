package com.lu.shiro;

import com.lu.shiro.spring.AbstractSpringTest;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/9.
 */
public class SpringShiroSpringTest extends AbstractSpringTest {

    @Test
    public void loginTest() {
        Subject login = login("root", "root");
        assert login.isAuthenticated();
        login.logout();
    }

    @Test
    public void testPermission() {
        Subject login = login("root", "root");
        assert login.isAuthenticated();
        assert login.isPermitted("123");
        login.logout();
    }

}
