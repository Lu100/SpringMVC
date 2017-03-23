package com.lu.operation.impl;

import com.lu.operation.Operation;

public enum BaseOperation implements Operation {
    PLUS("+") {
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, symbol, y, x + y));
        }
    },
    MINUS("-") {
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, getSymbol(), y, x - y));
        }
    },
    TIMES("*") {
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, getSymbol(), y, x * y));
        }
    },
    DIVIDE("/") {
        public void apply(double x, double y) {
            System.out.println(String.format(template, x, getSymbol(), y, x / y));
        }
    };

    String symbol;

    BaseOperation(String symbol) {
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
