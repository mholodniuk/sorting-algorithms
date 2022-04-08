import Structures.MovieList;
import Structures.Movie;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MovieListTest 
{
    @Test
    public void testReadFromFile() {
        MovieList movies = new MovieList();
        movies.readFromFile("src/main/java/resources/data_short.csv");
        
        Movie other = new Movie("Special OPS (2020– )", 7.0);
        assertTrue(movies.at(2).equals(other));
    }

    @Test
    public void testSwap() {
        MovieList movies = new MovieList();
        movies.readFromFile("src/main/java/resources/data_short.csv");
        
        movies.swap(1, 2);

        Movie other = new Movie("Special OPS (2020– )", 7.0);
        assertTrue(movies.at(1).equals(other));
    }
}
