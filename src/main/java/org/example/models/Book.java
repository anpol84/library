package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    private int book_id;
    @NotEmpty(message = "field must not be empty")
    private String title;
    @NotEmpty(message = "field must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+",
            message = "Full name should consist of 3 words with a capital letter")
    private String author;

    @Min(value = 1000, message = "year must be greater then 1000")
    private int year_of_creation;
    private Integer person_id;

    public Book(int book_id, String title, String author, int year, Integer person_id) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year_of_creation = year;
        this.person_id = person_id;
    }

    public Book() {
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_creation() {
        return year_of_creation;
    }

    public void setYear_of_creation(int year_of_creation) {
        this.year_of_creation = year_of_creation;
    }


}
