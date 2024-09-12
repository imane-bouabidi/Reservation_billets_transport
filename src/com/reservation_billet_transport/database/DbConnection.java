package com.reservation_billet_transport.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/eco_mooove";
    private static final String USER = "postgres";
    private static final String PASS = "Imane2002***";
    
    public static DbConnection instance=new DbConnection();

    private Connection connection;

    private DbConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
    public static DbConnection getInstance() {
    	return instance;
    }

    public Connection getConnection() {
        return connection;
    }


}
