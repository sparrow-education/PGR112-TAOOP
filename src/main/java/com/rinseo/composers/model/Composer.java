package com.rinseo.composers.model;

public class Composer {
    private String name;
    private int birth;
    private int death;
    private int age;

    public Composer(String name, int birth, int death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
        if (birth < death) {
            this.age = death - birth;
        } else {
            System.out.println("Birth year must be less than death year");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return """
                ======================================
                Name:   %s
                Age:    %d
                Birth:  %d
                Death:  %d
                ======================================
                """.formatted(name, age, birth, death);
    }
}
