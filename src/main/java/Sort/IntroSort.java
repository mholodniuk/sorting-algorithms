package Sort;

import java.util.ArrayList;

public class IntroSort {
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        introSort(tab, tab.size());
    }

    private static <T extends Comparable<T>> void introSort(ArrayList<T> tab, int n) {
        if(n <= 0) return;
        medianOfThree(tab.get(0), tab.get(1), tab.get(2));
    }

    private static <T extends Comparable<T>> T medianOfThree(T a, T b, T c) {
        return a;
    }


}