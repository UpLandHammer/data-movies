package br.com.datamovies;

import br.com.datamovies.http.client.impl.ImdbApiClientImpl;
import br.com.datamovies.models.Movie;
import br.com.datamovies.services.HTMLGenerator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Data Movies html page generator.");

        List<Movie> movies = new ImdbApiClientImpl().findTopMovies();

        new HTMLGenerator().generateHtmlPageMovies(movies);

        LOGGER.log(Level.INFO, "Html page generated.");


    }

}
