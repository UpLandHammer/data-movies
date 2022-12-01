package br.com.datamovies;

import br.com.datamovies.models.Content;
import br.com.datamovies.http.client.impl.ImdbApiClientImpl;
import br.com.datamovies.http.client.impl.MarvelApiClientImpl;
import br.com.datamovies.services.HTMLGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Data Movies html page generator.");


        List<? extends Content> movies = new ImdbApiClientImpl().findTopTitles();

        List<? extends Content> comics = new MarvelApiClientImpl().findTopTitles();

        List<Content> content = Stream.concat(movies.stream(), comics.stream()).collect(Collectors.toList());

        Collections.sort(content, Comparator.comparing(Content::getTitle));

        new HTMLGenerator().generateHtmlPageMovies(content);



        LOGGER.log(Level.INFO, "Html page generated.");


    }

}
