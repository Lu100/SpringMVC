package com.lu.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月11日  11:26
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月11日  11:26   Luyongjia
 *         new file.
 * </pre>
 */
@EnableAspectJAutoProxy
@Component
@Aspect
public class TimeCounterAdvice {

    private Logger logger = LoggerFactory.getLogger(TimeCounterAdvice.class);

    @Around("@annotation(com.lu.spring.annotation.TimeCounter)")
    public Object counter(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "->" + joinPoint.getSignature().getName();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            long exceptionTime = System.currentTimeMillis();
            logger.error("[{}]执行出现异常，耗时={}", methodName, calculateMillis(exceptionTime - start));
            throw e;
        }
        long end = System.currentTimeMillis();
        logger.info("[{}]执行完成，耗时={}", methodName, calculateMillis(end - start));
        return result;
    }

    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = MINUTE * 60;

    private static String calculateMillis(long millis) {
        StringBuilder builder = new StringBuilder();
        if (millis >= HOUR) {
            builder.append(calculateAndAppend(millis, "[%d]小时 ", HOUR));
            millis %= HOUR;
        }
        if (millis >= MINUTE) {
            builder.append(calculateAndAppend(millis, "[%d]分钟 ", MINUTE));
            millis %= MINUTE;
        }
        if (millis >= SECOND) {
            builder.append(calculateAndAppend(millis, "[%d]秒 ", SECOND));
            millis %= SECOND;
        }
        return millis != 0 ? builder.append(String.format("[%d]毫秒", millis)).toString() : builder.toString();
    }

    private static String calculateAndAppend(long number, String format, long duration) {
        return String.format(format, number / duration);
    }

}
