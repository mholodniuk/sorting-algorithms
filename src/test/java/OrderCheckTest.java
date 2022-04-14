import Structures.Movie;
import Benchmark.OrderCheck;
import Sort.QuickSort;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;

public class OrderCheckTest {

    @Test
    public void unorderedListTest() {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_1000.csv");
        
        assertFalse(OrderCheck.isInCorrectOrder(movies));
    }

    @Test
    public void orderedListTest() {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_1000.csv");
        QuickSort.sort(movies);
        assertTrue(OrderCheck.isInCorrectOrder(movies));
    }
}