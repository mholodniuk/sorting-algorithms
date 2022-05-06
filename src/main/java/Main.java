import java.sql.*;
import java.util.ArrayList;

import Benchmark.*;
import Structures.Movie;

public class Main {
    public static void main(String[] args) {
        DBDriver db = null;

        int sizes[] = {20000, 100000, 500000, 1000000};
        ArrayList<Movie> movies = null;

        try {
            db = new DBDriver();
            db.clearDataBase();
            
            for(int i = 0; i < 3; i++) {
                for(int size: sizes) {
                    ArrayList<Movie> unsortedMovies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
                    
                    movies = unsortedMovies;
                    db.runSingleSorting(movies, "merge", size);
                    
                    movies = unsortedMovies;
                    db.runSingleSorting(movies, "quick", size);
                    
                    movies = unsortedMovies;
                    db.runSingleSorting(movies, "intro", size);
    
                    movies = null;
                }
            }
            for(int size: sizes) {
                System.out.println("MergeSort average time: " + (int) db.getAverageTime("merge", size) + " for size: " + size);
                System.out.println("QuickSort average time: " + (int) db.getAverageTime("quick", size) + " for size: " + size);
                System.out.println("IntroSort average time: " + (int) db.getAverageTime("intro", size) + " for size: " + size);
                System.out.println();
            }
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
