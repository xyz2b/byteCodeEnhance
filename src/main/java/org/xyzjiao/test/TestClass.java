package org.xyzjiao.test;

public class TestClass {
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test(int i) {
        System.out.println(i);
    }

    public void test() {
        System.out.println("test");
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TestAttach{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
