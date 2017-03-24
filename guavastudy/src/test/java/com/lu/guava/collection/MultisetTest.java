package com.lu.guava.collection;


import com.google.common.collect.TreeMultiset;
import org.junit.Test;

/**
 * 元素计数集合
 */
public class MultisetTest {
    @Test
    public void test1() {
        TreeMultiset<String> comparables = TreeMultiset.create();
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("123123");
        comparables.add("12");
        comparables.add("12");
        comparables.add("12");
        comparables.add("12");
        System.out.println(comparables);
    }
}
