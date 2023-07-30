package org.example.dao;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class));

    }

    public Optional<Book> show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (title, author, year_of_creation) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear_of_creation());
    }

    public void update(Book book, int id){
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year_of_creation = ? WHERE book_id = ?",
                book.getTitle(), book.getAuthor(), book.getYear_of_creation(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
    }
    public void set(int personId, int bookId){
        System.out.println(bookId + " " + personId);
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id = ?", personId, bookId);
    }

    public void unset(int bookId){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", null, bookId);
    }

    public List<Book> getConcreteBooks(int personId){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
