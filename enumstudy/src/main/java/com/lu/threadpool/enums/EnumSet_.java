package com.lu.threadpool.enums;

import java.util.EnumSet;
import java.util.Set;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月23日  9:25
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月23日  9:25   Luyongjia
 *         new file.
 * </pre>
 */
public class EnumSet_ {
    public enum Style{BOLD,ITALIC,UNDERLINE,STRIKETHROUGH}
    public static void apply(Set<Style> styles){
        System.out.println("styles = [" + styles + "]");
    }

    public static void main(String[] args) {
        apply(EnumSet.of(Style.BOLD,Style.ITALIC));
    }
}
