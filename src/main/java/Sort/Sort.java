package Sort;

import java.util.ArrayList;

public interface Sort {
    public <T extends Comparable<T>> void sort(ArrayList<T> movies);
}
