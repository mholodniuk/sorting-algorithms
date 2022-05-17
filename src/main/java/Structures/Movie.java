package Structures;

import java.util.ArrayList;
import java.util.Collections;
import java.io.FileReader;
import com.opencsv.CSVReader;

import Benchmark.*;


public class Movie implements Comparable<Movie> {
    private String name;
    private double rating;

    public Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public static ArrayList<Movie> copyArray(ArrayList<Movie> movies) {
        ArrayList<Movie> copy = new ArrayList<Movie>(movies.size());
        copy.addAll(movies);
        return copy;
    }

    public static ArrayList<Movie> readMoviesFromFile(String filename, int size) {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        ArrayList<Movie> movies = new ArrayList<>();
        String[] records;
        int counter = 0;
        //double average = 0;

        try {
            fileReader = new FileReader(filename);
            csvReader = new CSVReader(fileReader);
            records = csvReader.readNext(); // reads first line - ",movie,rating"
            while((records = csvReader.readNext()) != null && counter < size) {
                if(!records[1].isEmpty() && !records[2].isEmpty()) {
                    counter++;
                    //average += Double.parseDouble(records[2]);
                    movies.add(new Movie(records[1], Double.parseDouble(records[2])));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error while trying to read file: " + filename);
            e.printStackTrace();
        }
        //System.out.println("Average of " + size + " elements equals " + average/counter);
        // System.out.println("Total movies inserted: " + counter);
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

    public static void calculateAddingTime() {
        Timer timer = new Timer(Timer.Precision.MILLISECONDS);
        int sizes[] = {10000, 50000, 100000, 500000, 800000, 1000000};

        for(int size: sizes) {
            timer.start();
            ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
            long duration = timer.stop();
            System.out.printf("Redaing file of size %d took: %d milliseconds\n", size, duration);   
        }
    }

    public static void calculateAverageRating() {
        int sizes[] = {10000, 50000, 100000, 500000, 800000, 1000000};

        for(int size: sizes) {
            ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
        }
    }

    public static void calculateMedianRating() {
        int sizes[] = {10000, 50000, 100000, 500000, 800000, 1000000};
        double median = 0;

        for(int size: sizes) {
            ArrayList<Movie> movies = Movie.readMoviesFromFile("src/main/resources/data.csv", size);
            Collections.sort(movies);
            if(movies.size() % 2 == 0) {
                int index1 = (movies.size() - 1) / 2;
                int index2 = movies.size() / 2;
                median = (movies.get(index1).getRating() + movies.get(index2).getRating()) / 2;
            } else {
                int index = (movies.size() - 1) / 2;
                median = movies.get(index).getRating();
            }
            System.out.println("Median of " + size + " equals " + median);
        }
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
        // Movie.calculateMedianRating();
        // Movie.readMoviesFromFile("src/main/resources/data.csv", 1000000);
    }
}