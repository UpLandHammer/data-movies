package br.com.datamovies;

import br.com.datamovies.models.Movie;
import br.com.datamovies.services.DataMovieInformation;
import br.com.datamovies.services.HTMLGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws FileNotFoundException {

        LOGGER.log(Level.INFO, "Data Movies ");

        DataMovieInformation dataMovieInformation = new DataMovieInformation();

        String topMovies = dataMovieInformation.getTopMovies("https://imdb-api.com/API/Top250Movies/");

        LOGGER.log(Level.INFO, "Printing movies list");

        List<Movie> movies = dataMovieInformation.getMoviesFrom(topMovies);

        PrintWriter printWriter = new PrintWriter("src/movie_list.html");

        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);

        htmlGenerator.generate(movies);

        printWriter.close();

        LOGGER.log(Level.INFO, "Movie list {0}", movies);

    }

}
