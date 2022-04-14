package Sort;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Movie;
import Benchmark.Timer;

public class BubbleSort {
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        int length = tab.size();
        for(int i = 0; i < length-1; ++i)
            for(int j = 0; j < length-i-1; ++j)
                if(tab.get(j).compareTo(tab.get(j + 1)) > 0)
                    Collections.swap(tab, j, j + 1);
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_1000.csv");
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        BubbleSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("BubbleSort took: %d milliseconds", duration);
    }
}
