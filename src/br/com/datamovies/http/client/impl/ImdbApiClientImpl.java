package br.com.datamovies.http.client.impl;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.http.client.ApiClient;
import br.com.datamovies.http.client.Content;
import br.com.datamovies.models.imdb.ImdbInformationMovies;
import br.com.datamovies.properties.ApplicationProperties;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ImdbApiClientImpl implements ApiClient {

    public static final String API_KEY_LABEL = "api_key_imdb";

    @Override
    public List<? extends Content> findTopTitles() {
        String topMovies = this.getResourceFrom("https://imdb-api.com/API/Top250Movies/");
        return this.toObject(topMovies);
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

    public List<? extends Content> toObject(String json) {
        Gson gson = new Gson();
        ImdbInformationMovies imdbInformationMovies = gson.fromJson(json, ImdbInformationMovies.class);
        return imdbInformationMovies.getItems();
    }
}
