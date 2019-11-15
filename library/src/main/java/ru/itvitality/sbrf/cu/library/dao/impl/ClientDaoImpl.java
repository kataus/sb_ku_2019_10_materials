package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.entities.Client;

import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private final SessionFactory sessionFactory;

    public ClientDaoImpl() {
        Configuration configuration = new Configuration()
                .configure( "hibernate.cfg.xml" );

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();

        Metadata metadata = new MetadataSources( serviceRegistry )
                .addAnnotatedClass( Client.class )
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @Override
    public void insert( Client client ) {
        try ( Session session = sessionFactory.openSession() ) {
            session.beginTransaction();
            session.save( client );
            System.out.println( ">>>>>>>>>>> created:" + client );

            System.out.println( ">>>>>>>>>>> before commit" );
            session.getTransaction().commit();

            // А тут select не выполняется, Person берется из кэша L1
            Client selected = session.get( Client.class, client.getId() );
            System.out.println( ">>>>>>>>>>> selected:" + selected );
        }

    }

    @Override
    public List<Client> list() {
        try ( Session session = sessionFactory.openSession() ) {
            return session.createQuery( "select u from Client u", Client.class ).list();

        }
    }
}
