package br.com.datamovies.http.client.impl;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.http.client.ImdbApiClient;
import br.com.datamovies.models.Movie;
import br.com.datamovies.properties.ApplicationProperties;
import br.com.datamovies.utils.DataMovieInformationUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ImdbApiClientImpl implements ImdbApiClient {

    public static final String API_KEY_LABEL = "api_key";

    @Override
    public List<Movie> findTopMovies() {
        DataMovieInformationUtils dataMovieInformationUtils = new DataMovieInformationUtils();
        String topMovies = this.getResourceFrom("https://imdb-api.com/API/Top250Movies/");
        return dataMovieInformationUtils.parseJsonToListMovies(topMovies);
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
