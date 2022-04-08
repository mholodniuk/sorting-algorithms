package Sort;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Movie;

public class BubbleSort {
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> movies) {
        int length = movies.size();
        for(int i = 0; i < length-1; ++i)
            for(int j = 0; j < length-i-1; ++j)
                if(movies.get(j).compareTo(movies.get(j + 1)) > 0)
                    Collections.swap(movies, j, j + 1);
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/java/resources/data_short.csv");
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
        BubbleSort.sort(movies);
        System.out.println("\nPosortowanie:");
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
    }
}
