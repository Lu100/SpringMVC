package com.lu.operation;

/**
 * 数学操作接口
 */
public interface Operation {
    String template = "%.2f %s %.2f = %.2f";
    /**
     * 计算方法
     *
     * @param x 参数X
     * @param y 参数Y
     */
    void apply(double x, double y);
}
