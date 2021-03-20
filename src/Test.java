import Services.MovieReview;
import model.Movie;
import model.User;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] main){

        MovieReview movieReview = new MovieReview();
        movieReview.addMovie("Don", "2006", "Action & Comedy");
        movieReview.addMovie("Tiger", "2008", "Drama");
        movieReview.addMovie("Padmaavat", "2006", "Comedy");
        movieReview.addMovie("LunchBox", "2022", "Drama");
        movieReview.addMovie("Guru", "2006", "Drama");
        movieReview.addMovie("Metro", "2006", "Romance");

        movieReview.addUser("SRK");
        movieReview.addUser("Salman");
        movieReview.addUser("Deepika");

        movieReview.addReview("SRK", "Don", 2.0);
        movieReview.addReview("SRK", "Padmaavat", 8.0);
        movieReview.addReview("Salman", "Don", 5.0);
        movieReview.addReview("Deepika", "Don", 9.0);
        movieReview.addReview("Deepika", "Guru", 6.0);
//        movieReview.addReview("SRK", "Don", 10.0);
//        movieReview.addReview("Deepika", "LunchBox", 5.0);
        movieReview.addReview("SRK", "Tiger", 5.0);
        movieReview.addReview("SRK", "Metro", 7.0);

        System.out.println(movieReview.getAvgReviewScoreMovie("Don"));
        System.out.println(movieReview.getAvgReviewScoreYear("2006"));

        List<Movie> topNMovies = movieReview.getTopMovies("Drama", 5);
        topNMovies.forEach(System.out::println);
        System.out.println();
        List<Movie> movies = movieReview.getMovies().values().stream().collect(Collectors.toList());
        movies.forEach(System.out :: println);
        List<User> users = movieReview.getUsers().values().stream().collect(Collectors.toList());
        users.forEach(System.out :: println);

    }
}
