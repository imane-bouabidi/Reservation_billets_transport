package com.reservation_billet_transport.database;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
    	DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            System.out.println("Connected to the database successfully!");
        } else {
            System.err.println("Failed to connect to the database!");
        }

        // Close the connection
//	        dbConnection.closeConnection();
    }

}
