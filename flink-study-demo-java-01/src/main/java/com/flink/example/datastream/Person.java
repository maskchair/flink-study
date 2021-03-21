package com.flink.example.datastream;

/**
 * @author Jack House
 * @date 2021年03月20日 11:04:48
 */
public class Person {

    public String name;

    public Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
