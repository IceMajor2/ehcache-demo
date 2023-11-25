package ehcache.java.demo.person;

public record Person(
        Long id,
        String firstName,
        String lastName,
        Integer age
) {

    public Person(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
