import Structures.Movie;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Arrays;

public class MovieTest {

    @Test
    public void compareArrayTest() {
        Movie m1 = new Movie("Hangover", 8.0);
        Movie m2 = new Movie("Piku", 5.0);
        Movie m3 = new Movie("Spider-Man", 9.0);
        Movie[] movies = {m1, m2, m3};
        Movie[] movies_expected = {m2, m1, m3};
        
        Arrays.sort(movies);
        assertArrayEquals(movies_expected, movies);
    }

    @Test
    public void compareToTest() {
        Movie m1 = new Movie("Hangover", 8.0);
        Movie m2 = new Movie("Piku", 5.0);

        int comparison = m1.compareTo(m2);
        assertTrue(comparison > 0);
    }

    @Test
    public void testReadFromFileWithSize() {
        LinkedList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", 10);
        
        Movie other = new Movie("The Droving (2020)", 2.0);
        assertTrue(movies.get(4).equals(other) && movies.size() == 10);
    }
}
