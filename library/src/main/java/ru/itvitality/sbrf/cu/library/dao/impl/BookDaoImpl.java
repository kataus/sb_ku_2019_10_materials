package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Client;

public class BookDaoImpl extends DaoImpl implements BookDao {

    @Override
    public void putBook(Book book, Client toClient) {
        try ( Session session = sessionFactory.openSession() ) {
            session.beginTransaction();
            book.setHolder(toClient);
            session.saveOrUpdate( book );
            System.out.println( ">>>>>>>>>>> put:" + book );

            System.out.println( ">>>>>>>>>>> before commit" );
            session.getTransaction().commit();

        }

    }
}
