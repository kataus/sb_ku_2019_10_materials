package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.itvitality.sbrf.cu.library.dao.Dao;
import ru.itvitality.sbrf.cu.library.entities.Librarian;
import ru.itvitality.sbrf.cu.library.entities.LibrarianInsertable;
import ru.itvitality.sbrf.cu.library.entities.impl.BookImpl;
import ru.itvitality.sbrf.cu.library.entities.impl.ClientImpl;
import ru.itvitality.sbrf.cu.library.entities.impl.HumanImpl;
import ru.itvitality.sbrf.cu.library.entities.impl.LibrarianImpl;

abstract public class DaoImpl implements Dao {
    static final protected SessionFactory sessionFactory = (new MetadataSources(new StandardServiceRegistryBuilder()
                    .applySettings(new Configuration()
                        .configure("hibernate.cfg.xml")
                        .getProperties())
                    .build())
                .addAnnotatedClass(ClientImpl.class)
                .addAnnotatedClass(BookImpl.class)
                .addAnnotatedClass(LibrarianImpl.class)
                .getMetadataBuilder()
                .build())
            .getSessionFactoryBuilder()
            .build();

    @Override
    public <T> void insert(T t)
    {
        try ( Session session = sessionFactory.openSession() ) {
            session.beginTransaction();
            session.save( t );
            System.out.println( ">>>>>>>>>>> created:" + t );

            System.out.println( ">>>>>>>>>>> before commit" );
            session.getTransaction().commit();

        }

    }
}
