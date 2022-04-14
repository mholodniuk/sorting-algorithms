package Sort;

import java.util.ArrayList;

public class IntroSort {
    
    public static <T extends Comparable<T>> void sort(ArrayList<T> tab) {
        introSort(tab, tab.size());
    }

    public static <T extends Comparable<T>> void introSort(ArrayList<T> tab, int n) {
        if(n <= 0) return;
        
    }
}