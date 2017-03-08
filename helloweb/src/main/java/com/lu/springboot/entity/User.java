package com.lu.springboot.entity;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月08日  11:53
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月08日  11:53   Luyongjia
 *         new file.
 * </pre>
 */
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
