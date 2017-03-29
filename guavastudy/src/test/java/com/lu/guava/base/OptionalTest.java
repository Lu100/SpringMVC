package com.lu.guava.base;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by Administrator on 2017/3/24.
 */
public class OptionalTest {

    @Test
    public void optionalTest() {
        Optional<Integer> optional = Optional.of(1);
        if (optional.isPresent()) {
            System.out.println("possible isPresent:" + optional.isPresent());
            System.out.println("possible value:" + optional.get());
        }
    }

    //判断空值，并使用默认值

    @Test
    public void testNonNull(){
        Optional<Object> empty = Optional.empty();
        empty.ifPresent(System.out::println);
        System.out.println(empty.orElse("hello"));
    }

    @Test(expected = NullPointerException.class)
    public void InsertNullValue(){
        Optional<Object> o = Optional.of(null);
        o.ifPresent(System.out::println);
    }

}