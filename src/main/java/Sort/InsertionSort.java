package Sort;

import java.util.ArrayList;

import Structures.Movie;
import Benchmark.OrderCheck;
import Benchmark.Timer;

public class InsertionSort {
    public static final String NAME = "insertionsort";
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        int length = tab.size();
        for(int i = 1; i < length; i++) {
            T key = tab.get(i);
            int j = i - 1;
            while(j >= 0 && tab.get(j).compareTo(key) > 0) {
                tab.set(j + 1, tab.get(j));
                j = j - 1;
            }
            tab.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 1000);
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);

        timer.start();
        InsertionSort.sort(movies);
        long duration = timer.stop();

        if(OrderCheck.isInCorrectOrder(movies))
            System.out.printf("InsertionSort took: %d milliseconds", duration);
    }
}
