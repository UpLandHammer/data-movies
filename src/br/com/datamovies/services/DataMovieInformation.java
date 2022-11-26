package br.com.datamovies.services;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.properties.ApplicationProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataMovieInformation {

    public static final String API_KEY_LABEL = "api_key";

    public String getTopMovies(String url) {

        return getResourceFrom(url);
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
