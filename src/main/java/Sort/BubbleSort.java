package Sort;

import Structures.MovieList;

public class BubbleSort implements Sort {

    public BubbleSort() {}

    @Override
    public void sort(MovieList movies) {
        int length = movies.getLength();
        for(int i = 0; i < length-1; ++i)
            for(int j = 0; j < length-i-1; ++j)
                if(movies.at(j).compareTo(movies.at(j + 1)) > 0)
                    movies.swap(j, j + 1);
    }

    public static void main(String[] args) {
        MovieList movies = new MovieList();
        BubbleSort sort = new BubbleSort();
        movies.readFromFile("src/main/java/resources/data_short.csv");
        movies.print();

        System.out.println("\n\nPosortowane:");

        sort.sort(movies);
        movies.print();
    }
}
