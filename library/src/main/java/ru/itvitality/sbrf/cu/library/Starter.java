package ru.itvitality.sbrf.cu.library;

import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.impl.*;
import ru.itvitality.sbrf.cu.library.entities.*;

public class Starter {
    public static void main( String args[] ) {
        ClientDao clientDao = new ClientDaoImpl();

        Client client = new Client();
        client.setName( "Test1" );
        clientDao.insert( client );

        client = new Client();
        client.setName( "test2" );
        clientDao.insert( client );

        clientDao.list().forEach( c -> System.out.println( c ) );
    }
}
