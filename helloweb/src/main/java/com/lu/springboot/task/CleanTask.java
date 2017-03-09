package com.lu.springboot.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 定时任务开启</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月09日  11:06
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月09日  11:06   Luyongjia
 *         new file.
 * </pre>
 */
@EnableScheduling
@Component
public class CleanTask {

    @Scheduled(cron = "0/1 * * * * ? ")
    public void sayHello() {
        System.out.println(nowString());
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    private String nowString() {
        return simpleDateFormat.format(new Date());
    }

}
