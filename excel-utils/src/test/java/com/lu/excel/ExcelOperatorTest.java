package com.lu.excel;

import com.google.common.collect.Lists;
import com.lu.excel.entity.User;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ExcelOperatorTest {
    @Test
    public void testOperator() throws IOException {
        ExcelOperator<User> userExcelOperator = ExcelOperator.newExcelOperator(User.class);
        userExcelOperator.append(users());
        userExcelOperator.append(users());
        userExcelOperator.append(users());
        userExcelOperator.append(users());
        userExcelOperator.append(users());
        File ok = userExcelOperator.ok("E://测试.xls");
        System.out.println(ok.getAbsolutePath());
    }

    private Random random = new Random();

    private Collection<User> users() {
        List<User> userList = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(1);
            user.setIdentityNumber(random.nextLong());
            user.setName("lu");
            user.setBirthday(new Date());
            userList.add(user);
        }
        return userList;
    }

}