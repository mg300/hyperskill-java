package org.example;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2DAO implements AutoCloseable, DBObj {
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
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS car (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) UNIQUE NOT NULL,company_id INT,FOREIGN KEY (company_id) REFERENCES company(id))");

    }

    public boolean addCompany(String companyName) throws SQLException {
        try {
            String query = "INSERT INTO company (name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, companyName);
            preparedStatement.executeUpdate();
            return true;
        } catch (JdbcSQLIntegrityConstraintViolationException err) {
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

    public String[] getCarsByCompany(String companyName) throws SQLException {
        List<String> cars = new ArrayList<>();
        PreparedStatement pstmt = conn.prepareStatement("SELECT car.name FROM car JOIN company ON car.company_id = company.id WHERE company.name = ?");
        pstmt.setString(1, companyName);
        ResultSet res = pstmt.executeQuery();
        while (res.next()) {
            String carName = res.getString("name");
            cars.add(carName);
        }
        return cars.toArray(new String[0]);
    }

    public boolean addCar(String companyName, String carName) throws SQLException {
        try {
            String findCompanyIdQuery = "SELECT id FROM company WHERE name = ?";
            String insertCarQuery = "INSERT INTO car (name, company_id) VALUES (?, ?)";

            PreparedStatement findCompanyStmt = conn.prepareStatement(findCompanyIdQuery);
            findCompanyStmt.setString(1, companyName);
            ResultSet rs = findCompanyStmt.executeQuery();

            if (rs.next()) {
                int companyId = rs.getInt("id");

                PreparedStatement insertCarStmt = conn.prepareStatement(insertCarQuery);
                insertCarStmt.setString(1, carName);
                insertCarStmt.setInt(2, companyId);
                insertCarStmt.executeUpdate();
                return true;

            } else {
                System.out.println("Company not found: " + companyName);
                return false;
            }
        } catch (JdbcSQLIntegrityConstraintViolationException err) {
            return false;
        }

    }

    @Override
    public void close() throws Exception {
        stmt.close();
        conn.close();
    }
}
