package org.example;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2DAO implements AutoCloseable, DBObj{
    private final Connection conn;
    private final Statement stmt;

    public H2DAO() throws Exception {
        String URL = "jdbc:h2:/Users/mateuszg/Desktop/hyperskill-java/Car sharing/h2";
        conn = DriverManager.getConnection(URL);
        stmt = conn.createStatement();
        conn.setAutoCommit(true);
        initialize();
    }
    private void initialize() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS company (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) UNIQUE NOT NULL)");

    }
    public boolean addCompany(String companyName) throws SQLException {
        try{
        String query = "INSERT INTO company (name) VALUES (?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, companyName);
        preparedStatement.executeUpdate();
        return true;
        }catch (JdbcSQLIntegrityConstraintViolationException err){
            return false;
        }
    }
    public String[] getCompanies() throws SQLException {
        List<String> companies = new ArrayList<>();
        ResultSet res = stmt.executeQuery("SELECT name FROM company");
        while (res.next()) {
            String companyName = res.getString("name");
            companies.add(companyName);
        }
        return companies.toArray(new String[0]);
    }
    @Override
    public void close() throws Exception {
        stmt.close();
        conn.close();
    }
}
