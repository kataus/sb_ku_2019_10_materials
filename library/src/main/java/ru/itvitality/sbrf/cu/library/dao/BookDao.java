package ru.itvitality.sbrf.cu.library.dao;

import ru.itvitality.sbrf.cu.library.entities.Book;

import java.util.List;

public interface BookDao {
    void add( Book book );

    List<Book> list();

    Book get( Long id );

    void remove( Long id );

    List<Book> getBooksOnHolder(Long holderId);

    void merge( Book book );
}
