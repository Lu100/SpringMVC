package com.lu.guava.collection;

import com.google.common.collect.ImmutableList;
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

}
