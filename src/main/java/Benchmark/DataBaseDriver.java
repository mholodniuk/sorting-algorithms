package Benchmark;

import java.sql.*;
import Sort.*;


public class DataBaseDriver {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql1";

    public double getPerformance(String type, int size) {
        int counter = 0;
        double average = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sort", USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(" SELECT * FROM performance WHERE name='" + type + "' AND size=" + size);

            while(result.next()) {
                counter++;
                average += result.getInt("time"); // ale chyba trzeba jeszcze rozwazyc rozne dlugosci tablic
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            //e.printStackTrace();
        } 
        return (average/counter);
    }

    public static void main(String[] args) {
        DataBaseDriver db = new DataBaseDriver();
        System.out.println("Average of four mergesorts: " + db.getPerformance(MergeSort.NAME, 1234));  
    }
}
