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

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> tab, int left, int right) {
        int leftIndex = left, rightIndex = right;
        T pivot = tab.get(left);

        while(leftIndex <= rightIndex) {
            while(tab.get(leftIndex).compareTo(pivot) < 0)
                ++leftIndex;
            while(tab.get(rightIndex).compareTo(pivot) > 0)
                --rightIndex;
            
            if(leftIndex <= rightIndex) {
                Collections.swap(tab, leftIndex, rightIndex);
                ++leftIndex;
                --rightIndex;
            }
        }
        if(left < rightIndex)
            quickSort(tab, left, rightIndex);
            
        if(right > leftIndex)
            quickSort(tab, leftIndex, right);
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 200000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        QuickSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("QuickSort took: %d milliseconds", duration);
    }
}
