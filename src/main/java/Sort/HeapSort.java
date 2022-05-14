package Sort;

import java.util.LinkedList;
import java.util.Collections;

import Benchmark.Timer;
import Structures.Movie;

public class HeapSort {
    public static final String NAME = "heapsort";
    
    public static <T extends Comparable<T>> void sort(LinkedList<T> tab) {
        heapSort(tab); 
    }

    private static <T extends Comparable<T>> void heapSort(LinkedList<T> tab) {
        int sizeOfHeap = tab.size();
        if(sizeOfHeap < 1) 
            return;
        
        for(int i = (sizeOfHeap - 1) / 2; i >= 0; i--) {
            heapify(tab, i, sizeOfHeap);
        }

        for(int i = sizeOfHeap - 1; i > 0; i--) {
            Collections.swap(tab, 0, i);
            heapify(tab, 0, i);
        }
    }

    private static <T extends Comparable<T>> void heapify(LinkedList<T> tab, int i, int size) {
        int left = 2 * i;
        int right = 2 * i + 1;
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
        LinkedList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        HeapSort.sort(movies);
        long duration = timer.stop();

        System.out.printf("Heapsort took: %d milliseconds\n", duration);
    }
}