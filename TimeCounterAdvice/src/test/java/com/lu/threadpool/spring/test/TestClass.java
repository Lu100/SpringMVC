package com.lu.threadpool.spring.test;

import com.lu.threadpool.spring.annotation.TimeCounter;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月11日  11:46
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月11日  11:46   Luyongjia
 *         new file.
 * </pre>
 */
@Component
public class TestClass {
    @TimeCounter
    public void testMethod() throws InterruptedException {
        Thread.sleep(1500);
    }

    @TimeCounter
    public void testException() {
        throw new RuntimeException();
    }

    /**
     * 测试基本数据类型调用时间
     */
    @TimeCounter
    public void testPrimitiveTypeExecute() {
        long a = 0;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            a += i;
        }
        System.out.println(a);
    }

    /**
     * 测试包装类型调用时间
     */
    @TimeCounter
    public void testWrapperExecute() {
        Long a = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            a += i;
        }
        System.out.println(a);
    }

}
