package dev.omedia.db;

import dev.omedia.db.models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Executor {
    private final DBConnection dbConnection = new DBConnection();


    public List<Customer> getCustomer() {
        try (Connection conn = dbConnection.getConnection()) {
            ResultSet resultSet = conn.createStatement().executeQuery("select * FROM w3schools.customers");
            int nColumn = resultSet.getMetaData().getColumnCount();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= nColumn; i++) {
                stringBuilder.append(resultSet.getMetaData().getColumnName(i));
                stringBuilder.append(i == nColumn ? "\n" : ",");
            }
            while (resultSet.next()) {
                for (int i = 1; i <= nColumn; i++) {
                    stringBuilder.append(resultSet.getString(i));
                    stringBuilder.append(i == nColumn ? "\n" : ",");
                }
            }
            System.out.println(stringBuilder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
