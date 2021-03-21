package com.flink.example.datastream;


import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author Jack House
 * @date 2021年03月20日 10:09:57
 */
public class Test {

    public static void main(String[] args) {
        Tuple2<String, Integer> person = Tuple2.of("Jack", 15);
        String name = person.f0;
        Integer age = person.f1;
        System.out.println("姓名：" + name + "，年龄：" + age);

        Person person1 = new Person("James", 19);
    }
}
