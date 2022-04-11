package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Benchmark.Timer;
import Structures.Movie;

public class HeapSort {
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        heapSort(tab);
    }

    public static <T extends Comparable<T>> void heapSort(ArrayList<T> tab) {
        makeHeap(tab);
        int sizeOfHeap = tab.size() - 1;
        for(int i = sizeOfHeap; i > 0; i--) {
            Collections.swap(tab, 0, i);
            sizeOfHeap--;
            heapify(tab, 0, sizeOfHeap);
        }
    }

    public static <T extends Comparable<T>> void heapify(ArrayList<T> tab, int i, int size) {
        int right = 2 * i + 2;
        int left = 2 * i + 1;
        if(left > size || right > size) 
            return;

        T leftElement = tab.get(left);
        T rightElement = tab.get(right);
        int max;

        if(left <= size && leftElement.compareTo(tab.get(i)) > 0)
            max = Arrays.asList().indexOf(leftElement);
        else
            max = Arrays.asList().indexOf(tab.get(i));

        if(right <= size && rightElement.compareTo(tab.get(max)) > 0)
            max = Arrays.asList().indexOf(rightElement);

        if(tab.get(max).compareTo(tab.get(i)) != 0) {
            Collections.swap(tab, i, max);
            heapify(tab, max, size);
        }
    }

    public static <T extends Comparable<T>> void makeHeap(ArrayList<T> tab) {
        for(int i = tab.size()/2; i >= 0; i--)
            heapify(tab, i, tab.size() - 1);
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_10.csv");
        Timer timer = new Timer(Timer.Precision.NANO);
        HeapSort.makeHeap(movies);
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
        timer.start();
        //HeapSort.sort(movies);
        long duration = timer.stop();
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
        System.out.printf("HeapSort took: %d nanoseconds", duration);
    }

}