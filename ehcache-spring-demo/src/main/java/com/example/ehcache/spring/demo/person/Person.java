package com.example.ehcache.spring.demo.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("PERSON")
public record Person(
        @Id Long id,
        String firstName,
        String lastName,
        Integer age
) implements Serializable {

    public Person(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
