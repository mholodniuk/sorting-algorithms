package Sort;

import java.util.ArrayList;

import Structures.Movie;
import Benchmark.Timer;

public class MergeSort {
    public static final String NAME = "mergesort";

    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        mergeSort(tab, tab.size());
    }

    private static <T extends Comparable<T>> void mergeSort(ArrayList<T> tab, int n) {
        if(n < 2) 
            return;
        int mid = n / 2;
        ArrayList<T> left_tab = new ArrayList<>();
        ArrayList<T> right_tab = new ArrayList<>();

        for(int i = 0; i < mid; i++) {
            left_tab.add(i, tab.get(i));
        }
        for(int i = mid; i < n; i++) {
            right_tab.add(i - mid, tab.get(i));
        }
        mergeSort(left_tab, mid);
        mergeSort(right_tab, n - mid);
        merge(left_tab, right_tab, tab, mid, n - mid);
    }

    private static <T extends Comparable<T>> void merge(
        ArrayList<T> left_tab, ArrayList<T> right_tab, ArrayList<T> tab, int left, int right) {
        
        int i = 0, j = 0, k = 0;
        while(i < left && j < right) {
            if(left_tab.get(i).compareTo(right_tab.get(j)) <= 0)
                tab.set(k++, left_tab.get(i++));
            else 
                tab.set(k++, right_tab.get(j++));
        }
        while(i < left) {
            tab.set(k++, left_tab.get(i++));
        }
        while(j < right) {
            tab.set(k++, right_tab.get(j++));
        }
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        MergeSort.sort(movies);
        long afterUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long duration = timer.stop();

        System.out.printf("MergeSort took: %d milliseconds\t memory usage: %d", duration, afterUsedMem-beforeUsedMem);
    }
}
