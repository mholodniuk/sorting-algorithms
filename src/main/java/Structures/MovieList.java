package Structures;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.opencsv.CSVReader;

public class MovieList {
    private ArrayList<Movie> movies;

    public MovieList() {
        this.movies = new ArrayList<>();
    }

    public MovieList(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void readFromFile(String filename) {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        String[] records;

        try {
            fileReader = new FileReader(filename);
            csvReader = new CSVReader(fileReader);
            // reads first line - ",movie,rating"
            records = csvReader.readNext();
            while((records = csvReader.readNext()) != null) {
                movies.add(new Movie(records[1], Double.parseDouble(records[2])));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            try {
                fileReader.close();
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    public int getLength() {
        return movies.size();
    }

    public Movie at(int idx) {
        return movies.get(idx);
    }

    public void insert(int idx, Movie movie) {
        movies.add(idx, movie);
    }

    public void swap(int i, int j) {
        Collections.swap(this.movies, i, j);
    }

    public void print() {
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
    }
    public static void main(String[] args) {
        MovieList movies = new MovieList();
        movies.readFromFile("src/main/java/resources/data_short.csv");
        movies.print();
    }
}
