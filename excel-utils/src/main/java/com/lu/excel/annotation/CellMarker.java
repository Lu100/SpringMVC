package com.lu.excel.annotation;

import com.lu.excel.handler.AbstractCellHandler;

import java.lang.annotation.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 单元格注解</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  10:38
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  10:38   Luyongjia
 *         new file.
 * </pre>
 * 可以标记：<br/>
 * 1、成员变量,请参考测试类中的[com.lu.excel.entity.User]<br/>
 * 2、静态变量,请参考测试类中的[com.lu.excel.entity.User]<br/>
 * 3、无参数方法,请参考测试类中的[com.lu.excel.entity.User]<br/>
 * 4、其他注解,请参考测试类中的[com.lu.excel.annotation.CustomAnnotation]<br/>
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CellMarker {
    /**
     * 默认长度为32
     */
    int defaultWidth = 1 << 5;

    /**
     * 顺序 数字越小越靠前
     */
    int sequence() default 1 << 10;

    /**
     * 单元格标题
     */
    String title() default "";

    /**
     * 单元格宽度
     */
    int width() default defaultWidth;

    /**
     * 指定处理器类型
     */
    Class<? extends AbstractCellHandler> handler();

}
