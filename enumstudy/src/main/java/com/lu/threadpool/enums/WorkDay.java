package com.lu.threadpool.enums;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月20日  14:24
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月20日  14:24   Luyongjia
 *         new file.
 * </pre>
 */
public enum WorkDay {

    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday(DayType.week_end), Sunday(DayType.week_end);

    private static final int base_work_hours = 8;
    private DayType dayType;

    double pay(double dailyWage, int workHours) {
        if (dayType == DayType.work_day) return workHours / base_work_hours * dailyWage;
        if (dayType == DayType.week_end) return dailyWage + workHours / base_work_hours;
        return 0;
    }


    WorkDay() {
        this(DayType.work_day);
    }

    WorkDay(DayType daytype) {
        this.dayType = daytype;
    }


    private enum DayType {
        work_day, week_end, holiday
    }

    public static void main(String[] args) {
        System.out.println(Monday.pay(1, 2));
    }
}
