package com.lu.guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by Administrator on 2017/3/24.
 */
public class ImmutableSetTest {
    @Test(expected = UnsupportedOperationException.class)
    public void test1() {
        Collection of = ImmutableList.of("1", "2", "3");
        of.remove(1);
    }

    @Test
    public void test2(){
        Collection of=ImmutableList.of("1","2","3","5","4");
        ImmutableSet immutableSet = ImmutableSet.copyOf(of);
        System.out.println(immutableSet);

    }

}
