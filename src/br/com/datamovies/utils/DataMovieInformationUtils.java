package br.com.datamovies.utils;

import br.com.datamovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataMovieInformationUtils {

    public List<String> getDataInformationByLabel(String jsonContent, String label) {

        List<String> listOfLabel = new ArrayList<>();
        String[] arrayMovies = getArrayMoviesFrom(jsonContent);
        for (String arrayMovie : arrayMovies) {
            String[] attributesArray = arrayMovie.split(",");
            for (String attribute : attributesArray) {
                if (attribute.contains(label)) {
                    listOfLabel.add(getContentFromAttribute(attribute));
                }
            }
        }
        return listOfLabel;
    }

    public List<Movie> parseJsonToListMovies(String jsonContent) {
        List<Movie> movies = new ArrayList<>();
        String[] arrayMoviesFrom = getArrayMoviesFrom(jsonContent);
        for (String movieObject : arrayMoviesFrom) {
            Movie movie = buildMovieFromObjectString(movieObject);

            if (movie.hasContent()) {
                movies.add(movie);
            }
        }
        return movies;
    }

    private Movie buildMovieFromObjectString(String movieObject) {
        Movie movie = new Movie();
        String[] movieAttributes = movieObject.split("\",");

        for (String movieAttribute : movieAttributes) {

            if (movieAttribute.contains("title")) {
                movie.setTitle(getContentFromAttribute(movieAttribute));
            }
            if (movieAttribute.contains("image")) {
                movie.setUrlImage(getContentFromAttribute(movieAttribute));
            }
            if (movieAttribute.contains("year")) {
                movie.setYear(getContentFromAttribute(movieAttribute));
            }
            if (movieAttribute.contains("\"imDbRating\"")) {
                movie.setRating(getContentFromAttribute(movieAttribute));
            }
            if (movieAttribute.contains("\"rank\"")) {
                movie.setRankPosition(getContentFromAttribute(movieAttribute));
            }
        }
        return movie;
    }

    private String[] getArrayMoviesFrom(String jsonContent) {
        return jsonContent.split("\\[")[1].split("\\{");
    }

    private String getContentFromAttribute(String content) {
        String[] split = content.split("\"");
        return split[3];
    }




}
