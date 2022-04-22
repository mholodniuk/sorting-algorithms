package Benchmark;

import java.sql.*;
import java.util.ArrayList;

import Sort.*;
import Structures.Movie;


public class DataBaseDriver {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql1";
    private Connection connection = null;

    public DataBaseDriver() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sort", USERNAME, PASSWORD);
    }

    public double getPerformance(String type, int size) throws SQLException {
        int counter = 0;
        double average = 0;
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM performance WHERE name='" + type + "' AND size=" + size);

        while(result.next()) {
            counter++;
            average += result.getInt("time"); // ale chyba trzeba jeszcze rozwazyc rozne dlugosci tablic
            System.out.println(
                "name: " + result.getString("name") + 
                "time: " + result.getInt("time")  + 
                "size: " + result.getInt("size"));
        } 
        return (average/counter);
    }

    public void runSingleSorting(String type, int size) throws SQLException {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_" + size + ".csv");
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        switch(type) {
            case "merge":
                MergeSort.sort(movies);
                break;
            case "quick":
                QuickSort.sort(movies);
                break;
            case "intro":
                IntroSort.sort(movies);
                break;
            case "bubble":
                BubbleSort.sort(movies);
                break;
            case "heap":
                HeapSort.sort(movies);
                break;
            case "insert":
                InsertionSort.sort(movies);
                break;
            default:
                System.out.println("No such sorting algorithm implemented");
                return;
        }
        long duration = timer.stop();

        PreparedStatement prep = connection.prepareStatement("INSERT into performance(name, time, size) values (?, ?, ?)");
        prep.setString(1, type);
        prep.setString(2, Long.toString(duration));
        prep.setString(3, Integer.toString(size));

        prep.executeUpdate();
    }

    public static void main(String[] args) {
        DataBaseDriver db = null;
        try {
            db = new DataBaseDriver();

            for(int i = 0; i < 10; ++i) {
                db.runSingleSorting("quick", 1000);
                db.runSingleSorting("merge", 1000);
                db.runSingleSorting("bubble", 1000);
            }
        }
        catch(SQLException e) {
            System.out.println("Unable to connect to database or handle a query");
            e.printStackTrace();
        }
    }
}
