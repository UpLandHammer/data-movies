package br.com.datamovies;

import br.com.datamovies.services.DataMovieInformation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Data Movies ");

        DataMovieInformation dataMovieInformation = new DataMovieInformation();

        String topMovies = dataMovieInformation.getTopMovies("https://imdb-api.com/API/Top250Movies/");

        LOGGER.log(Level.INFO, "Movies title list.");

        dataMovieInformation
                .getDataInformationByLabel(topMovies, "title")
                .forEach(Application::printLogger);

        LOGGER.log(Level.INFO, "List of urls for movie poster images.");

        dataMovieInformation
                .getDataInformationByLabel(topMovies, "image")
                .forEach(Application::printLogger);

        dataMovieInformation
                .getDataInformationByLabel(topMovies, "year")
                .forEach(Application::printLogger);

    }

    private static void printLogger(String content) {
       LOGGER.log(Level.INFO, content);
    }
}
