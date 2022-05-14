package Sort;

import java.util.LinkedList;
import java.util.Collections;

import Structures.Movie;
import Benchmark.Timer;

public class BubbleSort {
    public static final String NAME = "bubblesort";

    public static <T extends Comparable<T>> void sort(LinkedList<T> tab) {
        int length = tab.size();
        for(int i = 0; i < length-1; ++i)
            for(int j = 0; j < length-i-1; ++j)
                if(tab.get(j).compareTo(tab.get(j + 1)) > 0)
                    Collections.swap(tab, j, j + 1);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            LinkedList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 1000);
            Timer timer = new Timer(Timer.Precision.MILLISECONDS);

            timer.start();
            BubbleSort.sort(movies);
            long duration = timer.stop();

            System.out.printf(i + ". bubbleSort took: %d milliseconds\n", duration);
        }
    }
}
