package ru.itvitality.sbrf.cu.library;

import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.impl.BookDaoImpl;
import ru.itvitality.sbrf.cu.library.dao.impl.ClientDaoImpl;
import ru.itvitality.sbrf.cu.library.entities.Client;

public class Starter {
    public static void main( String args[] ) {
        BookDao bookDao = new BookDaoImpl();
        //ClientDao clientDao = new ClientDaoImpl();

//        Client client = new Client();
//        client.setName( "Test1" );
//        clientDao.insert( client );
//
//        client = new Client();
//        client.setName( "test2" );
//        clientDao.insert( client );
//
//        clientDao.list().forEach( c -> System.out.println( c ) );
    }
}
