package ru.itvitality.sbrf.cu.library.dao.impl;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.entities.Book;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BookDaoImpl extends AbstractDaoImpl implements BookDao {


    @Override
    public void add( Book book ) {
        em.getTransaction().begin();
        em.persist( book );
        em.getTransaction().commit();
    }

    @Override
    public List<Book> list() {
        return em.createQuery( "select b from Book b", Book.class ).getResultList();
    }

    @Override
    public Book get( Long id ) {
        Book book = em.find( Book.class, id );
        return book.getDeleted() == 1 ? null : book;
    }

    @Override
    public void remove( Long id ) {

        Book book = em.find( Book.class, id );
        if (book != null){
            em.getTransaction().begin();
            book.setDeleted( 1 );
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Book> getBooksOnHolder( Long holderId ) {
        //TODO * implement
        return null;
    }

    @Override
    public void merge( Book book ) {

        Book bookCur = em.find( Book.class, book.getId() );
        if (bookCur != null){
            em.getTransaction().begin();
            bookCur.setAuthor( book.getAuthor() );
            bookCur.setName( book.getName() );
            bookCur.setIsbn( book.getIsbn() );
            em.getTransaction().commit();
        }

    }
}
