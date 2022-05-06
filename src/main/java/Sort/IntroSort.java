package Sort;

import java.util.ArrayList;
import java.util.Collections;

import Benchmark.Timer;
import Structures.Movie;

public class IntroSort {
    public static final String NAME = "introsort";
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        int maxDepth = (int)(2 * Math.floor(Math.log(tab.size())/Math.log(2)));
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

    private static <T extends Comparable<T>> int medianOfThree(int a, int b, int c) {
        int max = Math.max(Math.max(a, b), c);
        int min = Math.min(Math.min(a, b), c);
        int median = max ^ min ^ a ^ b ^ c;
        if (median == a)
            return a;
        if (median == b)
            return b;
        return c;
    }

    private static <T extends Comparable<T>> int randomPartition(ArrayList<T> tab, int low, int high) {
        int pivotIndex = (int) Math.random() % (high - low + 1) + low;
        T tmp = tab.get(pivotIndex);
        Collections.swap(tab, pivotIndex, high);

        int currentIndex = low;
        for(int i = low; i <= high - 1; i++) {
            if(tab.get(i).compareTo(tmp) <= 0) {
                Collections.swap(tab, currentIndex, i);
                currentIndex++;
            }
        }
        Collections.swap(tab, currentIndex, high);
        return currentIndex;
    }

    private static <T extends Comparable<T>> int partition(ArrayList<T> tab, int low, int high) {
        T pivot = tab.get(high);
        int partIndex = low;

        for(int i = low; i < high; i++) {
            if(tab.get(i).compareTo(pivot) <= 0) {
                partIndex++;
                Collections.swap(tab, partIndex, i);
            }
        }
        Collections.swap(tab, partIndex, high);
        
        return partIndex;
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 100000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        IntroSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("IntroSort took: %d milliseconds", duration);
    }
}