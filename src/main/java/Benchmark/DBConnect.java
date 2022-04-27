package Benchmark;

import java.sql.*;

class DBConnect {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql1";

    public static Connection Connect() {
        try {
            // Connect to mysql library
            //Class.forName("com.mysql.jdbc.Driver");
            // jdbc:mysql://hostname:port/databasename, server username, server password
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sort", USERNAME, PASSWORD);
            return conn;
            
        } catch (Exception ex) {
            // If connection fail
            System.out.println("Erro: " + ex);
            ex.printStackTrace();
            return null;
        }
    }
}
