package ru.itvitality.sbrf.cu.demo.cp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

/*
 * 1) Ограничить число коннектов в пуле = 3 шт.
 * Во время работы приложения, через jConsole постепенно увеличивать количество коннектов до 6.
 * !!! Коннекты создаются не сразу. Надо подождать пока не увидим увеличение количества работающих с коннектами потоков в логе
 *
 * */

public class ConnectionPoolDemo {
    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private final DataSource dataSource;


    public ConnectionPoolDemo() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setConnectionTimeout(60000); //ms This property controls the maximum number of milliseconds that a client (that's you) will wait for a connection from the pool.
        config.setIdleTimeout(2000); //ms This property controls the maximum amount of time that a connection is allowed to sit idle in the pool.
        config.setMinimumIdle(1); // This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool.
        config.setMaximumPoolSize(3); // This property controls the maximum size that the pool is allowed to reach, including both idle and in-use connections.
        config.setAutoCommit(true);
        config.setMaxLifetime(600000);//ms This property controls the maximum lifetime of a connection in the pool.
        config.setPoolName("DemoHiPool"); // This property represents a user-defined name for the connection pool.
        config.setRegisterMbeans(true); // This property controls whether or not JMX Management Beans ("MBeans") are registered or not. Default: false.
        dataSource = new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException {
        ConnectionPoolDemo demo = new ConnectionPoolDemo();
        demo.createTable();
        demo.insertRecords();
        demo.useConnectionPool();
    }

    private void createTable() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("create table test(id int, name varchar(50))")) {
            pst.executeUpdate();
        }
    }

    private void insertRecords() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("insert into test(id, name) values (?, ?)")) {
            Savepoint savePoint = connection.setSavepoint("savePointName");
            try {
                int rowCount = 0;
                for (int idx = 0; idx < 100; idx++) {
                    pst.setInt(1, idx);
                    pst.setString(2, "NameValue_" + idx);
                    rowCount += pst.executeUpdate();
                }
                connection.commit();
                System.out.println("inserted rowCount:" + rowCount);
            } catch (SQLException ex) {
                connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }

    private void useConnectionPool() {
        for (int i = 0; i < 5; i++) {
            new Thread(this::doSelect).start();
        }
    }

    private void doSelect() {
        while (true) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement pst = connection.prepareStatement("select count(*) as counter from test")) {
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(Thread.currentThread().getName() + "  " + rs.getString("counter"));
                    }
                }
                Thread.sleep(3_000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
