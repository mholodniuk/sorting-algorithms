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
        double sum = 0;
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM performance WHERE name='" + type + "' AND size=" + size);

        while(result.next()) {
            counter++;
            sum += result.getInt("time");
            System.out.println(
                "name: " + result.getString("name") + 
                ", time: " + result.getInt("time")  + 
                "ms, size: " + result.getInt("size"));
        } 
        return (sum/counter);
    }

    public <T extends Comparable<T>> void runSingleSorting(ArrayList<T> list, String type, int size) throws SQLException {
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        switch(type) {
            case "merge":
                MergeSort.sort(list);
                break;

            case "quick":
                QuickSort.sort(list);
                break;

            case "intro":
                IntroSort.sort(list);
                break;

            case "bubble":
                BubbleSort.sort(list);
                break;

            case "heap":
                HeapSort.sort(list);
                break;

            case "insert":
                InsertionSort.sort(list);
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
        int size = 1000;
        try {
            db = new DataBaseDriver();
            
            for(int i = 0; i < 3; ++i) {
                ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data.csv", size);

                db.runSingleSorting(movies, "quick", size);

                movies = null;
            }
            db.getPerformance("quick", 10000);  
        }
        catch(SQLException e) {
            System.out.println("Unable to connect to database or handle a query");
            e.printStackTrace();
        }
    }
}