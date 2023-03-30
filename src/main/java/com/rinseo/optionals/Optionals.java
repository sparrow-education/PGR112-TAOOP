package com.rinseo.optionals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Optionals {
    static ArrayList<Dog> dogs = new ArrayList<>();
    record Dog(
            String name,
            String breed,
            int age
    ){}

    static {
        Dog dog = new Dog("Fido", "Pointer", 2);
        Dog dog2 = new Dog("Hachiko", "Akita", 3);
        dogs.addAll(List.of(dog, dog2));
    }

    public static Optional<Dog> findDogByName(String name) {
        return dogs.stream()
                .filter(dog -> dog.name().equals(name))
                .findFirst();
    }

    public static void main(String[] args) {
        var result = findDogByName("Fido");

        if (result.isPresent()) {
            System.out.println(result.get().name());
        } else {
            System.out.println("No dog found");
        }
    }

}
