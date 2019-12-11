package ru.itvitality.sbrf.cu.library.service.impl;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.dto.BookDescription;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.service.BookService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl( BookDao bookDao ) {
        this.bookDao = bookDao;
    }

    @Override
    public Collection<BookDescription> getBookDescriptions() {
        Set<BookDescription> bookSet = new HashSet<>(  );
        for (Book book : bookDao.list()){
            bookSet.add( BookDescription.build( book ) );
        }
        return bookSet;

    }

    @Override
    public Book get( Long id ) {
        return bookDao.get( id );
    }

    @Override
    public void update( Book book ) {
        bookDao.merge( book );
    }

    @Override
    public void add( Book book ) {
        bookDao.add( book );
    }
}
