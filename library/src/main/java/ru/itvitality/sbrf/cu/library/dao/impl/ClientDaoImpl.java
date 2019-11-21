package ru.itvitality.sbrf.cu.library.dao.impl;

import org.hibernate.Session;
import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends AbstractDaoImpl implements ClientDao {

    @Override
    public void insert( Client client ) {
        //TODO implement
    }

    @Override
    public List<Client>  list() {
        //TODO implement
        return new ArrayList<>(  );
    }
}
