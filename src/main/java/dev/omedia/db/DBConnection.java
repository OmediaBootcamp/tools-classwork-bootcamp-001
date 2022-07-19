package dev.omedia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
    private final String user="postgres";
    private final String password="LONDONIStval1.";


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,user,password);
    }

}
