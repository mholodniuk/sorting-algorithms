package Structures;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;


public class Movie implements Comparable<Movie> {
    private String name;
    private double rating;

    public Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public static ArrayList<Movie> readMoviesFromFile(String filename) {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        ArrayList<Movie> movies = new ArrayList<>();
        String[] records;

        try {
            fileReader = new FileReader(filename);
            csvReader = new CSVReader(fileReader);
            records = csvReader.readNext(); // reads first line - ",movie,rating"
            while((records = csvReader.readNext()) != null) {
                if(!records[1].isEmpty() && !records[2].isEmpty())
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
        return movies;
    }

    @Override
    public int compareTo(Movie other) {
        return (int)(this.getRating() - other.getRating());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
               "\nRating: " + this.rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
            return false;
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = Movie.readMoviesFromFile("resources/data_10.csv");
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
    }
}
