package com.lu.excel.entity;

import com.lu.excel.annotation.CustomAnnotation;
import com.lu.excel.annotation.CellMarker;
import com.lu.excel.support.IntegerCellHandler;
import com.lu.excel.support.LongCellHandler;
import com.lu.excel.support.SimpleDateCellHandler;
import com.lu.excel.support.StringCellHandler;

import java.util.Date;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  10:55
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  10:55   Luyongjia
 *         new file.
 * </pre>
 */
public class User {
    @CellMarker(handler = StringCellHandler.class, title = "性别", sequence = 2, width = 1 << 3)
    public static final String GENDER = "男";
    @CellMarker(handler = StringCellHandler.class, title = "姓名", sequence = 10)
    private String name;
    @CellMarker(handler = IntegerCellHandler.class, title = "年龄")
    private Integer age;
    @CellMarker(handler = LongCellHandler.class, title = "身份证", width = 1 << 5, sequence = 3)
    private long identityNumber;
    @CellMarker(handler = SimpleDateCellHandler.class, title = "出生日期", width = 1 << 5, sequence = 2)
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getIdentityNumber() {
        return identityNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setIdentityNumber(long identityNumber) {
        this.identityNumber = identityNumber;
    }

    @CellMarker(handler = StringCellHandler.class, title = "备注", width = 1 << 5, sequence = 1)
    public String remark() {
        return "呵呵！！";
    }

    @CustomAnnotation
    public String custom;
}
