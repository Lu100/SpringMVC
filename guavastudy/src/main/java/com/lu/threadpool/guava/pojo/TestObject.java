package com.lu.threadpool.guava.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;


public class TestObject implements Comparable<TestObject> {
    private int age;
    private String name;
    private boolean isChecked;

    public TestObject(int age, String name, boolean isChecked) {
        this.age = age;
        this.name = name;
        this.isChecked = isChecked;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int compareTo(TestObject that) {
        return ComparisonChain.start().compare(this.name,that.name).result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return age == that.age &&
                isChecked == that.isChecked &&
                com.google.common.base.Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(age, name, isChecked);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("age", age)
                .add("isChecked", isChecked)
                .add("name", name)
                .toString();
    }
}
