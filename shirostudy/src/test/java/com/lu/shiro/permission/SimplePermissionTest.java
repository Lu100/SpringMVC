package com.lu.shiro.permission;

import com.lu.shiro.AbstractShiroTest;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class SimplePermissionTest extends AbstractShiroTest {
    @Override
    public String iniPath() {
        return "classpath:permission/simplePermission.ini";
    }

    @Test
    public void testRole() {
        Subject login = login("root", "root");
        assert login.isAuthenticated();
        assert login.hasRole("role1");
        assert login.hasRole("role2");
    }

    @Test
    public void testPermission() {
        Subject login = login("lu", "123456");

        assert login.isAuthenticated();
        //lu是role1角色
        assert login.hasRole("role1");
        //拥有创建权限
        assert login.isPermitted("user:create");
        //没有删除就权限
        assert !login.isPermitted("user:delete");
    }

    @Test
    public void checkPermission() {
        Subject login = login("lu", "123456");
        assert login.isAuthenticated();
        assert login.isPermitted("system:user:create");
        assert login.isPermitted("system:user:update");
        assert login.isPermitted("system:user:delete");
        assert login.isPermitted("system:user:view");
    }

    @Test
    public void checkFatherPermission() {
        Subject father = login("father", "father");
        assert father.isAuthenticated();
        //father的权限使用*匹配
        assert father.isPermitted("system:user:view");
        assert father.isPermitted("system:user:search");
        assert father.isPermitted("system:user:search,create");
    }
}
