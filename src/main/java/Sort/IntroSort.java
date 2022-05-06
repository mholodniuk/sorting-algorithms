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
            return;
        }
        if(maxDepth == 0) {
            HeapSort.sort(tab);
            return;
        } 
        int pivot = medianOfThree(begin, begin + size/2 + 1 , end);
        Collections.swap(tab, pivot, end);
        int partition_idx = partition(tab, begin, end);

        introSort(tab, begin, partition_idx - 1, maxDepth - 1);
        introSort(tab, partition_idx + 1, end, maxDepth - 1);
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
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 1000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        IntroSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("IntroSort took: %d milliseconds", duration);
    }
}