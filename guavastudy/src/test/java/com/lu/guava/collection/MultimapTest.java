package com.lu.guava.collection;


import com.google.common.base.CharMatcher;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Random;

/**
 * Multimap 是一种键值-队列类型数据结构
 * <p>
 * 一般来说，Multimap接口应该用第一种方式看待，但asMap()视图返回Map<K, Collection<V>>，让你可以按另一种方式看待Multimap。重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在Multimap中。
 * <p>
 * 很少会直接使用Multimap接口，更多时候你会用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
 */
public class MultimapTest {
    private String name1 = "queue1";
    private String name2 = "queue2";
    private String name3 = "queue3";
    private String name4 = "queue4";

    @Test
    public void test1() {
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        for (int i = 0, bound = random.nextInt(32); i < bound; i++) insert(multimap, name1);
        for (int i = 0, bound = random.nextInt(32); i < bound; i++) insert(multimap, name2);
        for (int i = 0, bound = random.nextInt(32); i < bound; i++) insert(multimap, name3);
        for (int i = 0, bound = random.nextInt(32); i < bound; i++) insert(multimap, name4);
        System.out.println(multimap);
    }

    private void insert(Multimap<String, Integer> multimap, String name) {
        multimap.put(name, random());
    }

    private static final int RANDOM_BOUND = 1024;

    private Random random = new Random();

    private Integer random() {
        return random.nextInt(RANDOM_BOUND);
    }
}
