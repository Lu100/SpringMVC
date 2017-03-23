package com.lu.operation.impl;

import com.lu.operation.Operation;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月23日  10:50
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月23日  10:50   Luyongjia
 *         new file.
 * </pre>
 */
public enum ExtensionOperations implements Operation {
    POW("^") {
        @Override
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, symbol, y, Math.pow(x, y)));
        }
    },
    MOD("%") {
        @Override
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, symbol, y, x % y));
        }
    };


    String symbol;

    ExtensionOperations(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
