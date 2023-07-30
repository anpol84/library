package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int person_id;
    @NotEmpty(message = "field must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+",
            message = "Full name should consist of 3 words with a capital letter")
    private String name;

    @Min(value = 1920, message = "year must be greater then 1920")
    private int year_of_birth;

    public Person(int person_id, String name, int year_of_birth) {
        this.person_id = person_id;
        this.name = name;
        this.year_of_birth = year_of_birth;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }
}
