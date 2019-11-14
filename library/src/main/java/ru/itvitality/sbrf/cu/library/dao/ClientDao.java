package ru.itvitality.sbrf.cu.library.dao;

import ru.itvitality.sbrf.cu.library.entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    void insert( Client client ) throws SQLException;

    List<Client> list() throws SQLException;

    void close() throws SQLException;

    void createTable() throws SQLException;

}
