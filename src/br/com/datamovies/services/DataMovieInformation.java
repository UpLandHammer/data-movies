package br.com.datamovies.services;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.models.Movie;
import br.com.datamovies.properties.ApplicationProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DataMovieInformation {

    public static final String API_KEY_LABEL = "api_key";

    public String getTopMovies(String url) {
        return getResourceFrom(url);
    }

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

    public List<Movie> getMoviesFrom(String jsonContent) {
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

    private String getResourceFrom(String url) {
        try {
            ApplicationProperties applicationProperties = new ApplicationProperties();
            String apiKey = applicationProperties.getPropertiesApplication(API_KEY_LABEL);
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest
                    .newBuilder(new URI(url + apiKey))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (URISyntaxException uriSyntaxException) {
            throw new DataMovieInformationException(String.format("The url %s entered syntax errors", url), uriSyntaxException);
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DataMovieInformationException(String.format("Fail to get data from %s", url), e);
        }
    }
}
