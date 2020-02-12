package ru.itvitality.sbrf.cu.library.dao;

import ru.itvitality.sbrf.cu.library.entities.*;

import java.util.List;

public interface ClientDao {

    void insert( Client client );

    List<Client> list();

}
