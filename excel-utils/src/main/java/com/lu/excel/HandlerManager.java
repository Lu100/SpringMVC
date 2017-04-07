package com.lu.excel;

import com.lu.excel.handler.AbstractCellHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:处理器管理器</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  11:44
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  11:44   Luyongjia
 *         new file.
 * </pre>
 */
class HandlerManager {

    private static final HandlerManager HANDLER_MANAGER = new HandlerManager();

    private HandlerManager() {
    }

    static HandlerManager getInstance() {
        return HANDLER_MANAGER;
    }


    private final Map<Class<? extends AbstractCellHandler>, Object> manager = new HashMap<>();

    <T extends AbstractCellHandler> T getHandler(Class<T> handlerClass) {
        if (!manager.containsKey(handlerClass)) {
            try {
                synchronized (manager) {
                    manager.put(handlerClass, handlerClass.newInstance());
                }
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(handlerClass.getName() + "无法实例化");
            }
        }
        return (T) manager.get(handlerClass);
    }
}
