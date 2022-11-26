package br.com.datamovies;

import br.com.datamovies.services.DataMovieInformation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Data Movies");

        String topMovies = new DataMovieInformation().getTopMovies("https://imdb-api.com/API/Top250Movies/");

        LOGGER.log(Level.INFO, topMovies);

    }
}
