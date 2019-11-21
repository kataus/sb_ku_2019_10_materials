package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.entities.Client;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends AbstractDaoImpl implements ClientDao {

    @Override
    public void insert( Client client ) {
        System.out.println( ">>>>>> insert Client" );
        em.getTransaction().begin();
        em.persist( client );
        em.getTransaction().commit();

    }

    @Override
    public List<Client>  list() {
        System.out.println( ">>>>>> list of Clients" );
        return em.createQuery( "select c from Client c", Client.class )
                .getResultList();
    }
}
