package com.lu.threadpool.guava.foreach;

import com.google.common.collect.ImmutableSet;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/4/5.
 */
public enum SomeNumber {
    ONE, TWO, THREE, FOUR, FIVE, SIX;

    public static void main(String[] args) {
        System.out.println("---------------------------incorrect----------------------------");
        ImmutableSet<SomeNumber> someNumbers = ImmutableSet.copyOf(SomeNumber.values());
        //错误的示范
        for (Iterator<SomeNumber> a = someNumbers.iterator(); a.hasNext(); ) {
            for (Iterator<SomeNumber> b = someNumbers.iterator(); b.hasNext(); ) {
                //在b进行迭代的代码块内，a也在进行迭代，当内部循环完成时，a迭代器也迭代也到达最后一个元素
                System.out.println(a.next().toString() + " " + b.next().toString());
            }
        }
        System.out.println("---------------------------correct----------------------------");
        //为了防止上述不规范的迭代，应该使用下面的代码
        for (SomeNumber a : someNumbers) {
            for (SomeNumber b : someNumbers) {
                System.out.println(a.toString() + " " + b.toString());
            }
        }
    }
}
