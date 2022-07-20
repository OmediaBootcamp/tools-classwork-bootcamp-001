package dev.omedia.db;

import dev.omedia.db.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final String prefix = "select customerid, customername, contactname, address, city, postalcode, country" +
            " from w3schools.customers";

    public List<Customer> getCustomersByCountry(String country) {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(prefix + " where country=? and customerid=?");
            preparedStatement.setString(1, country);
            preparedStatement.setInt(2,1);
            return getCustomerList(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(prefix);
            return getCustomerList(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Customer> getCustomerList(PreparedStatement preparedStatement) {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getLong("customerid"),
                        resultSet.getString("customername"),
                        resultSet.getString("contactname"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("postalcode"),
                        resultSet.getString("country")
                ));
            }
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
