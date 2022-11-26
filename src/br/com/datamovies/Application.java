package br.com.datamovies;

import br.com.datamovies.models.Movie;
import br.com.datamovies.services.DataMovieInformation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Data Movies ");

        DataMovieInformation dataMovieInformation = new DataMovieInformation();

        String topMovies = dataMovieInformation.getTopMovies("https://imdb-api.com/API/Top250Movies/");

        LOGGER.log(Level.INFO, "Printing movies list");

        List<Movie> moviesFrom = dataMovieInformation.getMoviesFrom(topMovies);

        LOGGER.log(Level.INFO, "Movie list {0}", moviesFrom);

    }

}
