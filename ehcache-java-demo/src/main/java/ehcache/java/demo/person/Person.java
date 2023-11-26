package ehcache.java.demo.person;

import java.io.Serializable;

public record Person(
        Long id,
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
