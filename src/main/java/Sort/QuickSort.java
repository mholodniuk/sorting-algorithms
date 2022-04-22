package Sort;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Movie;
import Benchmark.Timer;

public class QuickSort {
    public static final String NAME = "quicksort";

    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        quickSort(tab, 0, tab.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> tab, int begin, int end) {
        if(begin < end) {
            int partition_idx = partition(tab, begin, end);

            quickSort(tab, begin, partition_idx - 1);
            quickSort(tab, partition_idx + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(ArrayList<T> tab, int begin, int end) {
        T pivot = tab.get(end);
        int i = begin - 1;

        for(int j = begin; j < end; j++) {
            if(tab.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(tab, i, j);
            }
        }
        Collections.swap(tab, i + 1, end);
        
        return i+1;
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_1000.csv");
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        MergeSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("QuickSort took: %d milliseconds", duration);
    }
}
