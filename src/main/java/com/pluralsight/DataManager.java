package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataManager {
    static PreparedStatement pStmt = null;
    static Connection conn = null;
    static BasicDataSource dataSource = new BasicDataSource();
    static Scanner myScanner = new Scanner(System.in);

    public static void startConnection() {
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("8371");
    }

    public static void newShipper() {
        System.out.println("Hello User, please insert the following data, name and phone.");
        String userInput = myScanner.nextLine().trim();
        String[] inputSplit = userInput.split(" ");

        try (Connection conn = dataSource.getConnection(); PreparedStatement queryEx = conn.prepareStatement("SELECT * FROM Shippers");
             PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Shippers(CompanyName,Phone) VALUES (?,?)")) {
            pStmt.setString(1, inputSplit[0]);
            pStmt.setString(2, inputSplit[1]);
            int rows = pStmt.executeUpdate();
            try (ResultSet rs = queryEx.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getString("ShipperID"));
                    System.out.println("Company Name: " + rs.getString("CompanyName"));
                    System.out.println("Phone: " + rs.getString("Phone"));
                    System.out.println("----------------------------");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateShipper() {
        System.out.println("Hello! Do you want to continue and update the phone number of a shipper? Yes or No ");
        String userChoice = myScanner.nextLine().trim();
        if (userChoice.equalsIgnoreCase("Yes")) {
            try (Connection conn = dataSource.getConnection(); PreparedStatement queryEx = conn.prepareStatement("SELECT * FROM Shippers")) {
                try (ResultSet rs = queryEx.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getString("ShipperID"));
                        System.out.println("Company Name: " + rs.getString("CompanyName"));
                        System.out.println("Phone: " + rs.getString("Phone"));
                        System.out.println("----------------------------");
                    }
                }

                System.out.println("Of the shippers above which would you like to change? Please enter the id and the phone number of the shipper.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Thank you and have a good day.");
            System.exit(0);
        }


    }
}
