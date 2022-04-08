package Structures;


public class Movie implements Comparable<Movie> {
    private String name;
    private double rating;

    public Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
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
               "\nRating: " + this.rating +
               "\n"; 
    }
}
