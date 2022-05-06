import java.sql.*;
import java.util.ArrayList;

import Benchmark.*;
import Structures.Movie;

public class Main {
    public static void main(String[] args) {
        DBDriver db = null;

        int sizes[] = {10000, 100000, 500000};
        ArrayList<Movie> movies = null;

        try {
            db = new DBDriver();
            db.clearDataBase();
            
            for(int size: sizes) {
                ArrayList<Movie> unsortedMovies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
                
                movies = unsortedMovies;
                db.runSingleSorting(movies, "merge", size);
                
                movies = unsortedMovies;
                db.runSingleSorting(movies, "quick", size);
                
                movies = unsortedMovies;
                db.runSingleSorting(movies, "intro", size);

                movies = unsortedMovies;
                db.runSingleSorting(movies, "heap", size);

                movies = null;
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
