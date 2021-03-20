package model;

public class Movie {

    private String movieId;
    private String title;
    private int year;
    private String genres;

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genres='" + genres + '\'' +
                '}';
    }
}
