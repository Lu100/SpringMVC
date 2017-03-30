package com.lu.threadpool.guava.collection;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/29.
 */
public class SetsTest {
    /**
     * 笛卡尔积测试
     */
    @Test
    public void cartesianProductTest() {
        HashSet<Integer> integers1 = Sets.newHashSet(1, 3, 4, 5, 7, 8, 9, 9, 9);
        HashSet<Integer> integers2 = Sets.newHashSet(2, 4, 6, 8, 8, 8, 8, 8, 8, 8);
        @SuppressWarnings("unchecked") Set<List<Integer>> lists = Sets.cartesianProduct(integers1, integers2);
        System.out.println("integers1.size() = " + integers1.size());
        System.out.println("integers2.size() = " + integers2.size());
        System.out.println(lists.size());

    }
}
