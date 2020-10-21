package edu.psu.ist311.sortinator;

public class Person {
    private String name;
    private Integer age;
    private Integer height;


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        //in inches
        this.height = 64;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s: (%s)", this.name, this.age);
    }

}

