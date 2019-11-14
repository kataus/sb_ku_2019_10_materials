package ru.itvitality.sbrf.cu.library.dao.impl;

import ru.itvitality.sbrf.cu.library.dao.ClientDao;
import ru.itvitality.sbrf.cu.library.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;

    //TODO - убрать исключение из конструктора (не try-catch)
    public ClientDaoImpl() throws SQLException {
        //TODO сделать базу хранимой на диске
        this.connection = DriverManager.getConnection( "jdbc:h2:mem:" );
        this.connection.setAutoCommit( false );
    }

    @Override
    public void insert( Client client ) throws SQLException {
        try ( PreparedStatement ps = connection.prepareStatement( "insert into user(id,name) values (?,?)" ) ) {
            ps.setInt( 1, client.getId() );
            ps.setString( 2, client.getName() );

            int rowCount = ps.executeUpdate();
            System.out.println( "inserted rowCount: " + rowCount );
            this.connection.commit();
        }

    }

    @Override
    public List<Client> list() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try ( PreparedStatement ps = this.connection.prepareStatement( "select id, name from user" ) ) {
            ResultSet resultSet = ps.executeQuery();

            while ( resultSet.next() ) {
                Client client = new Client();
                client.setId( resultSet.getInt( "id" ) );
                client.setName( resultSet.getString( "name" ) );
                clients.add( client );
            }

        }
        return clients;
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }

    @Override
    public void createTable() throws SQLException {
        try ( PreparedStatement ps = connection.prepareStatement( "create table user(id int, name varchar(100))" ) ) {
            ps.executeUpdate();
        }
    }
}
