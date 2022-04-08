import Structures.Movie;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieTest {

    @Test
    public void comparisonArray() {
        Movie m1 = new Movie("Hangover", 8.0);
        Movie m2 = new Movie("Piku", 5.0);
        Movie m3 = new Movie("Spider-Man", 9.0);
        Movie[] movies = {m1, m2, m3};
        Movie[] movies_expected = {m2, m1, m3};
        
        Arrays.sort(movies);
        assertArrayEquals(movies_expected, movies);
    }

    @Test
    public void comparisonSingle() {
        Movie m1 = new Movie("Hangover", 8.0);
        Movie m2 = new Movie("Piku", 5.0);

        int comparison = m1.compareTo(m2);
        assertTrue(comparison > 0);
    }

    @Test
    public void testReadFromFile() {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_short.csv");
        
        Movie other = new Movie("Special OPS (2020– )", 7.0);
        assertTrue(movies.get(2).equals(other));
    }
}
