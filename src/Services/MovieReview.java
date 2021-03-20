package Services;

import Exceptions.MultipleReviewException;
import Exceptions.NotReleasedException;
import model.Category;
import model.Movie;
import model.RatingMovie;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class MovieReview {

    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, List<RatingMovie>> ratingMovieList = new HashMap<>();

    public Map<String, Movie> getMovies() {
        return movies;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, List<RatingMovie>> getRatingMovieList() {
        return ratingMovieList;
    }

    public void addReview(String userName, String movieName, double rating){
        List<RatingMovie> ratingMovies = new ArrayList<>();
        if(movies.containsKey(movieName) && users.containsKey(userName)){
            RatingMovie ratingMovie = new RatingMovie();
            ratingMovie.setMovie(movieName);
            ratingMovie.setRating(rating);
            ratingMovie.setUserName(userName);
            if(ratingMovieList.containsKey(userName)){
                List<RatingMovie> existingRatingMovie = ratingMovieList.get(userName);

                if(existingRatingMovie.stream().filter(i -> i.getMovie() == movieName).count() > 0){
                    throw new MultipleReviewException("Multiple Reviews are not allowed");
                }
                int year = Calendar.getInstance().get(Calendar.YEAR);
                Movie movie = movies.get(movieName);
                if(movie.getYear() > year){
                    throw new NotReleasedException("Exception: movie yet to be released");
                }

                if(existingRatingMovie.size() > 2){
                    User user = users.get(userName);
                    user.setCategory(Category.Critic);
                    users.put(userName, user);
                }
                existingRatingMovie.add(ratingMovie);
                ratingMovies.addAll(existingRatingMovie);
            } else {
                ratingMovies.add(ratingMovie);
            }
            ratingMovieList.put(userName, ratingMovies);
        }
    }

    public void addMovie(String movieName, String year, String genre){
        Movie movie = new Movie();
        movie.setMovieId(movieName);
        movie.setTitle(movieName);
        movie.setYear(Integer.parseInt(year));
        movie.setGenres(genre);
        movies.put(movieName, movie);
    }

    public void addUser(String userName){
        User user = new User();
        user.setUserName(userName);
        user.setCategory(Category.Viewers);
        users.put(userName, user);
    }

    //Average review score in a particular year of release
    public double getAvgReviewScoreYear(String year){
        List<RatingMovie> ratingMovies = new ArrayList<>();
        for(Map.Entry<String, List<RatingMovie>> entry : ratingMovieList.entrySet()){
            ratingMovies.addAll(entry.getValue());
        }

        List<Movie> moviesList = new ArrayList<>(movies.values());
        List<Movie> moviesListYear = moviesList.stream().filter(i -> i.getYear() == Integer.parseInt(year)).collect(Collectors.toList());
        double sumOfReview = 0;
        int count = 0;
        for(Movie m : moviesListYear){
           sumOfReview += ratingMovies.stream().filter(i -> i.getMovie() == m.getMovieId()).mapToDouble(j -> j.getRating()).sum();
           count += ratingMovies.stream().filter(i -> i.getMovie() == m.getMovieId()).count();
        }

        return sumOfReview/count;
    }

    // Average review score for a particular Movie
    public double getAvgReviewScoreMovie(String movieName){
        List<RatingMovie> ratingMovies = new ArrayList<>();
        for(Map.Entry<String, List<RatingMovie>> entry : ratingMovieList.entrySet()){
            ratingMovies.addAll(entry.getValue());
        }
        return ratingMovies.stream().filter(i -> i.getMovie() == movieName).mapToDouble(j -> j.getRating()).average().getAsDouble();
    }

    // List top n movies by total review score by ‘critics’ in a particular genre.
    public List<Movie> getTopMovies(String genre, int n){
        List<RatingMovie> ratingMovies = new ArrayList<>();
        for(Map.Entry<String, List<RatingMovie>> entry : ratingMovieList.entrySet()){
            ratingMovies.addAll(entry.getValue());
        }

        List<Movie> moviesList = new ArrayList<>(movies.values());
        List<Movie> moviesListGenre = moviesList.stream().filter(i -> i.getGenres() == genre).collect(Collectors.toList());
        List<RatingMovie> filterByGenre = new ArrayList<>();
        for(Movie m: moviesListGenre){
            filterByGenre.addAll(ratingMovies.stream().filter(i -> i.getMovie() == m.getMovieId()).collect(Collectors.toList()));
        }

        filterByGenre.sort(Comparator.comparing(RatingMovie::getRating));

        List<Movie> topNMovies = new ArrayList<>();
        for(RatingMovie ratingMovie : filterByGenre){
            topNMovies.add(movies.get(ratingMovie.getMovie()));
        }
        return topNMovies.stream().limit(n).collect(Collectors.toList());
    }
}
