import Structures.Movie;
import Benchmark.OrderCheck;
import Sort.QuickSort;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.LinkedList;

public class OrderCheckTest {

    @Test
    public void unorderedListTest() {
        LinkedList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 1000);
        
        assertFalse(OrderCheck.isInCorrectOrder(movies));
    }

    @Test
    public void orderedListTest() {
        LinkedList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 1000);
        QuickSort.sort(movies);
        assertTrue(OrderCheck.isInCorrectOrder(movies));
    }
}