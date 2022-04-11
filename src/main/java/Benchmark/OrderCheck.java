package Benchmark;

import java.util.ArrayList;
import Structures.Movie;
import Sort.BubbleSort;

public class OrderCheck {
    public static <T extends Comparable<T>> boolean isInCorrectOrder(ArrayList<T> tab) {
        T previous = tab.get(0);
        for(T elem: tab) {
            if(previous.compareTo(elem) > 0) {
                return false;
            }
            previous = elem;
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_1000.csv");

        BubbleSort.sort(movies);
        boolean git = OrderCheck.isInCorrectOrder(movies);

        if(git)
            System.out.println("git");
        else
            System.out.println("nie git");

    }
}
