package model;

public class RatingMovie {
    private String movie;
    private String userName;
    private double rating;

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getMovie() {
        return movie;
    }

    public String getUserName() {
        return userName;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RatingMovie{" +
                "movie='" + movie + '\'' +
                ", userName='" + userName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
