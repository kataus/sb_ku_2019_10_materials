package ru.itvitality.sbrf.cu.library;

import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.impl.ClientDaoImpl;
import ru.itvitality.sbrf.cu.library.entities.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Starter {
    public static void main(String args[]) throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:h2:~/library");
        connection.setAutoCommit(false);
        ClientDao clientDao = new ClientDaoImpl(connection);

        clientDao.createTable();

        Client client = new Client();
        client.setName( "Test1" );
        client.setId( 1 );
        clientDao.insert( client );

        client = new Client();
        client.setName( "test2" );
        client.setId( 2 );
        clientDao.insert( client );

        clientDao.list().forEach( c -> System.out.println( c ) );
        // Ниже абсолютно тоже самое по староверски
//        for (Client client1 : clientDao.list()){
//            System.out.println( client1 );
//        }

        clientDao.close();
    }
}
