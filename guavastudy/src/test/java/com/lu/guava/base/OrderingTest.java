package com.lu.guava.base;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.ArrayList;

public class OrderingTest {


    @Test
    public void testOrdering() {
        MyOrdering myOrdering = new MyOrdering();
        ArrayList<String> strings = Lists.newArrayList("1", "4", "2", "3");
        strings.sort(myOrdering);
        System.out.println(strings);
    }

    class MyOrdering extends Ordering<String> {
        @Override
        public int compare(String left, String right) {
            int result = ComparisonChain.start().compare(left, right).result();
            System.out.println(String.format("[%s] compare to [%s] =[%d]", left, right, result));
            return result;
        }
    }
}
