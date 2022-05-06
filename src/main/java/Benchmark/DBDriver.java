package Benchmark;

import java.sql.*;
import java.util.ArrayList;

import Sort.*;
import Structures.Movie;


public class DBDriver {

    private Connection connection = null;

    public DBDriver() {
        connection = DBConnect.connect();
    }

    public void close() {
        try {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) {
            System.out.println("Can not close connection to database");
            e.printStackTrace();
        }
    }

    public double getAverageTime(String type, int size) throws SQLException {
        int counter = 0;
        double sum = 0;
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM performance WHERE name='" + type + "' AND size=" + size);

        while(result.next()) {
            counter++;
            sum += result.getInt("time");
            // System.out.println(
            //     "name: " + result.getString("name") + 
            //     ", time: " + result.getInt("time")  + 
            //     "ms, size: " + result.getInt("size"));
        }
        return (sum/counter);
    }

    public void clearDataBase() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "DELETE FROM performance;";

        statement.executeUpdate(query);
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
        DBDriver db = null;
        int size = 10000;
        try {
            db = new DBDriver();

            //db.clearDataBase();
            
            for(int i = 0; i < 3; ++i) {
                ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);

                db.runSingleSorting(movies, "quick", size);

                movies = null;
                size *= 2;
            }
            //db.getAverageTime("merge", size);  
        }
        catch(SQLException e) {
            System.out.println("Unable to connect to database or handle a query");
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }
}