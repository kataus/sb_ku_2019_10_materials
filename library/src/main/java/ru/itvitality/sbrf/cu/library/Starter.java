package ru.itvitality.sbrf.cu.library;

import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.impl.BookDaoImpl;
import ru.itvitality.sbrf.cu.library.dao.impl.ClientDaoImpl;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Client;

public class Starter {
    public static void main( String args[] ) {
        BookDao bookDao = new BookDaoImpl();
        ClientDao clientDao = new ClientDaoImpl();

        Client client = new Client();
        client.setName( "Test1" );
        client.setPhone( "1234" );
        clientDao.insert( client );

        client = new Client();
        client.setName( "test2" );
        client.setPhone( "1235" );
        clientDao.insert( client );

        clientDao.list().forEach( c -> System.out.println( c ) );

        Book book = new Book();
        book.setName( "Капитал" );
        book.setAuthor( "К. Маркс" );
        book.setIsbn( "123456" );
        bookDao.add( book );

        Long id = book.getId();

        bookDao.list().forEach( b -> System.out.println( ">>>> " + b ) );

        bookDao.remove( id );

        book = bookDao.get(id);

        System.out.println( "book = " + book );
    }
}
