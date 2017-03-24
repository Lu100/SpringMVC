package com.lu.guava;

import com.lu.guava.pojo.TestObject;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by Administrator on 2017/3/24.
 */
public class ObjectsTest {
    @Test
    public void toStringTest() {
        System.out.println(new TestObject(18, "lu", false));
    }

    @Test
    public void testEqual(){
        assert !Objects.equals(new TestObject(18, "x", false), new TestObject(18, "lu", false));
    }

    @Test
    public void testCompareTo(){
        System.out.println(new TestObject(18, "x", false).compareTo(new TestObject(18, "lu", false)));
    }
}
