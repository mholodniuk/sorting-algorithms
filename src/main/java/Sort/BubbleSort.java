package Sort;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Movie;
import Benchmark.Timer;

public class BubbleSort {
    public static final String NAME = "bubblesort";

    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        int length = tab.size();
        for(int i = 0; i < length-1; ++i)
            for(int j = 0; j < length-i-1; ++j)
                if(tab.get(j).compareTo(tab.get(j + 1)) > 0)
                    Collections.swap(tab, j, j + 1);
    }

    public static void main(String[] args) {
        final ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 5);

        System.out.println("Pobrana z pliku");
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }

        for(int i = 0; i < 3; i++) {
            
            ArrayList<Movie> moviesToSort = Movie.copyArray(movies);

            System.out.println("\n\nprzeniesiona do moviesToSort");
            for(Movie movie: moviesToSort) {
                System.out.println(movie.toString());
            }

            Timer timer = new Timer(Timer.Precision.MILLISECONDS);
            timer.start();
            BubbleSort.sort(moviesToSort);
            long duration = timer.stop();

            System.out.println("\n\nposortowana");
            for(Movie movie: moviesToSort) {
                System.out.println(movie.toString());
            }

            System.out.printf(i + ". bubbleSort took: %d milliseconds\n\n", duration);
        }
    }
}
