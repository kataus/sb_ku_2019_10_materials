package ru.itvitality.sbrf.cu.library;

import org.hibernate.SessionFactory;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.dao.impl.*;
import ru.itvitality.sbrf.cu.library.entities.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


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

        ClientDao clientDao = new ClientDaoImpl();
//        clientDao.setSessionFactory(sessionFactory);

        Client client = new Client();
        client.setName("Nobody");
        clientDao.insert(client);

        client = new Client();
        client.setName( "Test1" );
        clientDao.insert( client );

        client = new Client();
        client.setName( "test2" );
        clientDao.insert( client );

        ((ClientDaoImpl) clientDao).list().forEach(c -> System.out.println( c ) );
    }
}
