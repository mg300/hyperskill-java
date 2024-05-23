package org.example;

import java.sql.SQLException;

public interface DBObj {
    boolean addCompany(String name) throws SQLException;

    String[] getCompanies() throws SQLException;

    String[] getCarsByCompany(String companyName) throws SQLException;

    boolean addCar(String companyName, String carName) throws SQLException;

}
