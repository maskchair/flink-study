package com.flink.example.datastream;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Jack House
 * @date 2021年03月20日 11:08:20
 */
public class Example {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Person> flinstones = env.fromElements(new Person("Nico",25), new Person("Hores", 22), new Person("Jack", 28));

        DataStream<Person> adults = flinstones.filter(new FilterFunction<Person>() {
            @Override
            public boolean filter(Person person) throws Exception {
                return person.age >= 25;
            }
        });

        adults.print();

        env.execute();
    }

}
