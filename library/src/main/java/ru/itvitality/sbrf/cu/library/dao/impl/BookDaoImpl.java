package ru.itvitality.sbrf.cu.library.dao.impl;

import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.entities.Book;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDaoImpl extends AbstractDaoImpl implements BookDao {


    @Override
    public void add( Book book ) {
        em.persist( book );
    }

    @Override
    public List<Book> list() {
        return null;
    }

    @Override
    public Book get( Long id ) {
        return null;
    }

    @Override
    public void remove( Long id ) {

    }

    @Override
    public List<Book> getBooksOnHolder( Long holderId ) {
        return null;
    }
}
