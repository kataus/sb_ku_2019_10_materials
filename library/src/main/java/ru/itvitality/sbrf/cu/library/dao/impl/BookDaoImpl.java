package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.entities.Book;

public class BookDaoImpl extends DaoImpl implements BookDao {
    @Override
    public void insert(Book book) {
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(book);
            System.out.println(">>>> created" + book);
            session.getTransaction().commit();
        }
    }

/*    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }*/

    @Override
    public <T> void insert(T t) {

    }
}
