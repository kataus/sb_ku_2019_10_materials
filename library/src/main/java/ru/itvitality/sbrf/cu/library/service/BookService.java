package ru.itvitality.sbrf.cu.library.service;

import ru.itvitality.sbrf.cu.library.dto.BookDescription;
import ru.itvitality.sbrf.cu.library.entities.Book;

import java.util.Collection;
import java.util.List;

public interface BookService {

    Collection<BookDescription> getBookDescriptions();

    Book get( Long id );

    void update( Book book );

    void add( Book book );
}
