package com.rinseo.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * <T> annotates that this class is a generic type class
 * We can extend nested classes and interfaces to narrow down and constraint the generic types of 'T'
 * We can have multiple class parameters
 * Common design pattern for repositories
 * Using a collection to store data with CRUD operations
 * @param <T>
 */
public class Repository<T extends Repository.definingId<secondPara>, secondPara> {
    // record to model data 'Person'
    record Person(String firstName, String lastName, Long id) implements definingId<Long> {}
    private List<T> records = new ArrayList<>();

    List<T> getAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    /**
     * Predicate is a functional interface that takes virtually any type, in this case we defined 'person.id'
     * To specify the incoming streams of object 'id' type, we implement an interface/class of generic type with method to return self.
     * Then using lambda for each person in the stream, we access the 'id' of that object
     *
     * @param id
     * @return Boolean. if the person.id matches the id argument return true, else throw exception.
     */
    interface definingId<JustAnotherGeneric> {
        JustAnotherGeneric id();
    }
    T findById(long id) {
        return records
                .stream().filter(person -> person.id().equals(id)).findFirst().orElseThrow();
    }

    public static void main(String[] args) {
//        // Now we have to specify the actual TYPE of the generic class during instantiation
//        Repository<String> repo = new Repository<>();
//        repo.save("Artist");
//        repo.save("Photographer");
//        System.out.println(repo.getAll());

        // Now the generics will be enforced to be of type Person
        // Since class Repository requires 2 parameters, the first one being Person object, the second one we
        Repository<Person, Long> personRepo = new Repository<>();
        // saving error because method save is generic return type T, and T is Person now
        // personRepo.save("person");

        // A happy generic save()
        personRepo.save(new Person("Rin", "Seo", 10L));
        personRepo.save(new Person("John", "Doe",20L));
        System.out.println(personRepo.getAll());


        System.out.println(personRepo.findById(1));

        Person findThisPerson = personRepo.findById(10L);
        System.out.println(findThisPerson);
    }
}
