package Sort;

import java.util.ArrayList;

import Benchmark.Timer;
import Structures.Movie;

public class IntroSort {
    public static final String NAME = "introsort";
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        int maxDepth = (int)(2 * Math.floor( Math.log( tab.size() ) / Math.log(2) ) );
        introSort(tab, 0, tab.size() - 1, maxDepth);
    }

    private static <T extends Comparable<T>> void introSort(ArrayList<T> tab, int begin, int end, int maxDepth) {
        int size = end - begin;
        if(size < 16) {
            InsertionSort.sort(tab);
        }
        else if(maxDepth == 0) {
            HeapSort.sort(tab);
        }
        else {
            QuickSort.sort(tab);
        }
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        IntroSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("MergeSort took: %d milliseconds", duration);
    }
}