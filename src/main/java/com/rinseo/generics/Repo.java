package com.rinseo.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Design pattern for connecting to a database and querying it for retrieving data.
 * Having a list that can be storing generic type of data.
 */
public class Repo<T extends Repo.ID<V>, V> {
    // The class has parameter of T, when instantiated we can specify the type to reuse this class.
    private final List<T> records = new ArrayList<>();
    record Person(Long id, String fName, String lName) implements ID<Long>{}

    // CRUD OPS
    public List<T> getAll() {
        return records;
    }

    public T save(T record) {
        //TODO: Common pattern to check if ID exists and overwrite the save, otherwise save as new.
        records.add(record);
        return record;
    }

    // Defining an interface to retrieve "id" for generic type T. We have to extend this interface from the class
    // The parameter "U" can be named anything
    interface ID<U> { U id();}
    public T getById(long id) {
        // If we find legit "id" then return the record, otherwise throw an exception.
        return records.stream().filter(person -> person.id().equals(id)).findFirst().orElseThrow();
    }

    public static void main(String[] args) {
        // A class instance of type String.
//        Repo<String> repo = new Repo<>();
//
//        repo.save("Hello");
//        repo.save("World");

        // A class instance of type Person.
        Repo<Person, Long> personRepo = new Repo<>();
        personRepo.save(new Person(10L,"Jake", "Smith"));


        Person person1 = personRepo.getById(10L);
        System.out.println(person1);
    }
}
