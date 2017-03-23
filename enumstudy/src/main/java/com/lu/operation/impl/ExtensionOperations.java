package com.lu.operation.impl;

import com.lu.operation.Operation;

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
