package Structures;

import java.util.LinkedList;
import java.io.FileReader;
import com.opencsv.CSVReader;


public class Movie implements Comparable<Movie> {
    private String name;
    private double rating;

    public Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public static LinkedList<Movie> readMoviesFromFile(String filename) {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        LinkedList<Movie> movies = new LinkedList<>();
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
            System.out.println("Error while trying to read file: " + filename);
            e.printStackTrace();
        }
        return movies;
    }

    public static LinkedList<Movie> readMoviesFromFile(String filename, int size) {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        LinkedList<Movie> movies = new LinkedList<>();
        String[] records;
        int counter = 0;

        try {
            fileReader = new FileReader(filename);
            csvReader = new CSVReader(fileReader);
            records = csvReader.readNext(); // reads first line - ",movie,rating"
            while((records = csvReader.readNext()) != null && counter < size) {
                if(!records[1].isEmpty() && !records[2].isEmpty()) {
                    counter++;
                    movies.add(new Movie(records[1], Double.parseDouble(records[2])));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error while trying to read file: " + filename);
            e.printStackTrace();
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
        LinkedList<Movie> movies = Movie.readMoviesFromFile("resources/data.csv", 10);
        for(Movie movie: movies) {
            System.out.println(movie.toString());
        }
    }
}