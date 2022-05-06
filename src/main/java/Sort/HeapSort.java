package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Benchmark.Timer;
import Structures.Movie;

public class HeapSort {
    public static final String NAME = "heapsort";
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        heapSort(tab); 
    }

    private static <T extends Comparable<T>> void heapSort(ArrayList<T> tab) {
        if(tab.size() < 1) 
            return;
        int sizeOfHeap = tab.size();
        for(int i = (sizeOfHeap - 1) / 2; i >= 0; i--) {
            heapify(tab, i, sizeOfHeap);
        }
        for(int i = sizeOfHeap - 1; i > 0; i--) {
            Collections.swap(tab, 0, i);
            heapify(tab, 0, i);
        }
    }

    private static <T extends Comparable<T>> void heapify(ArrayList<T> tab, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if(left >= size || right >= size) 
            return;

        T leftElement = tab.get(left);
        T rightElement = tab.get(right);

        if(left < size && leftElement.compareTo(tab.get(largest)) > 0)
            largest = left;

        if(right < size && rightElement.compareTo(tab.get(largest)) > 0)
            largest = right;

        if(largest != i) {
            Collections.swap(tab, i, largest);
            heapify(tab, largest, size);
        }
    }

    public static void main(String[] args) {
        // ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10);
        ArrayList<Integer> mixed = new ArrayList<>(Arrays.asList(3, 9, 4, 12, 6, 5, 7, 10, 2));

        for(int elem: mixed) {
            System.out.println(elem + " ");
        }
        System.out.println(" \n\n");
        //Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        //timer.start();
        HeapSort.sort(mixed);
        //long duration = timer.stop();

        for(int elem: mixed) {
            System.out.println(elem + " ");
        }

        //System.out.printf("Heapsort took: %d milliseconds\n", duration);
    }
}