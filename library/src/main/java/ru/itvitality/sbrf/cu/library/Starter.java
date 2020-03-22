package ru.itvitality.sbrf.cu.library;

import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.LibrarianDao;
import ru.itvitality.sbrf.cu.library.dao.impl.*;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Librarian;
import ru.itvitality.sbrf.cu.library.entities.impl.BookImpl;
import ru.itvitality.sbrf.cu.library.entities.impl.ClientImpl;
import ru.itvitality.sbrf.cu.library.entities.impl.LibrarianImpl;


public class Starter {
    public static void main( String args[] ) {
/*        SessionFactory sessionFactory;
        Configuration configuration = new Configuration()
                .configure( "hibernate.cfg.xml" );

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();

        Metadata metadata = new MetadataSources( serviceRegistry )
                .addAnnotatedClass( Client.class )
                .addAnnotatedClass( Book.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();*/

//        ClientDao clientDao = new ClientDaoImpl();
        LibrarianDao librarianDao = new LibrarianDaoImpl();

        Librarian librarian = new LibrarianImpl("Larisa Anatolievna");
        librarianDao.insert(librarian);

        Book book = new BookImpl("Java persistent API and Hibernate","King");
        librarian.insert(book);

        ClientImpl clientImpl = new ClientImpl("Test1");
//        clientImpl.setName( "Test1" );
        librarian.insert(clientImpl);
        librarian.putBook(book,clientImpl);

        clientImpl = new ClientImpl("test2");
//        clientImpl.setName( "test2" );
        librarian.insert(clientImpl);

//        ((ClientDaoImpl) clientDao).list().forEach(c -> System.out.println( c ) );
    }
}
