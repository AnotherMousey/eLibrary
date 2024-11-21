package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "quanhhqt123");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
