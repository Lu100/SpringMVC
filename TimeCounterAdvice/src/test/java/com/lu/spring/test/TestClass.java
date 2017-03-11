package com.lu.spring.test;

import com.lu.spring.annotation.TimeCounter;
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
}
