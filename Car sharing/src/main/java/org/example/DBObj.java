package org.example;

import java.sql.SQLException;

public interface DBObj {
    boolean addCompany(String name) throws SQLException;
    String[] getCompanies() throws SQLException;
}
