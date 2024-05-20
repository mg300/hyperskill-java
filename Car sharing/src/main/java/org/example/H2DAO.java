package org.example;

import java.sql.*;

public class H2DAO implements AutoCloseable{
    private final Connection conn;
    private final Statement stmt;

    public H2DAO() throws SQLException {
        String URL = "jdbc:h2:/Users/mateuszg/Desktop/hyperskill-java/Car sharing/h2";
        conn = DriverManager.getConnection(URL);
        stmt = conn.createStatement();
        conn.setAutoCommit(true);
        initialize();
    }
    private void initialize() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS company (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");

    }

    @Override
    public void close() throws Exception {
        stmt.close();
        conn.close();
    }
}
