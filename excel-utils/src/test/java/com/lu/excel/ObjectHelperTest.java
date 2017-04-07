package com.lu.excel;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class ObjectHelperTest {

    @Test
    public void copy() throws Exception {
        A a = new A();
        B copy = ObjectHelper.copy(B.class, a);
        assert copy.a == a.a;
        assert copy.b.equals(a.b);
        assert copy.date.equals(a.date);
        System.out.println(a);
        System.out.println(copy);
    }

    private static Random random = new Random();

    private static class A {
        int a = random.nextInt();
        String b = String.valueOf(random.nextInt());
        Date date = new Date();
        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<C> cs = Lists.newArrayList(new C(1), new C(2), new C(3), new C(4), new C(5), new C(6));

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("a", a)
                    .add("b", b)
                    .add("date", date)
                    .add("integers", integers)
                    .add("cs", cs)
                    .toString();
        }
    }

    private static class B {
        List<Integer> integers;
        int a;
        String b;
        Date date;
        List<C> cs;

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("a", a)
                    .add("b", b)
                    .add("date", date)
                    .add("integers", integers)
                    .add("cs", cs)
                    .toString();
        }
    }

    private static class C {
        int a;

        public C(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("a", a)
                    .toString();
        }
    }

}