package com.lu.spring.aop;

import com.lu.spring.config.ScanConfig;
import com.lu.spring.test.TestClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月11日  12:30
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月11日  12:30   Luyongjia
 *         new file.
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScanConfig.class)
public class TimeCounterAdviceTest {
    @Autowired
    private TestClass testClass;

    @Test
    public void test() throws InterruptedException {
        testClass.testMethod();
    }

    @Test(expected = RuntimeException.class)
    public void testException() {
        testClass.testException();
    }

    @Test
    public void testPrimitiveType() {
        testClass.testPrimitiveTypeExecute();
        //将基本数据类型转换为包装类需要时间
        //两个方法实现功能相同，但是执行时间的差距很大
        testClass.testWrapperExecute();
    }

    /**
     * 会出现异常
     */
    @Test(expected = ArrayStoreException.class)
    public void errorCode() {
        Object[] objectArray = new Long[1];
        objectArray[0] = "Hello World";

    }
}
