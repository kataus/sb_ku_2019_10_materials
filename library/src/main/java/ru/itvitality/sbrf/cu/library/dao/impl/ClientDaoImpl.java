package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.entities.Client;

import java.util.List;

public class ClientDaoImpl extends DaoImpl implements ClientDao {

    public List<Client> list() {
        try ( Session session = sessionFactory.openSession() ) {
            return session.createQuery( "select u from Client u", Client.class ).list();

        }
    }
}
