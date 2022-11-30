package br.com.datamovies.http.client.impl;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.http.client.ApiClient;
import br.com.datamovies.http.client.Content;
import br.com.datamovies.models.imdb.marvel.Data;
import br.com.datamovies.models.imdb.marvel.DataMarvelInformationStories;
import br.com.datamovies.models.imdb.marvel.Result;
import br.com.datamovies.properties.ApplicationProperties;
import br.com.datamovies.utils.DataApiInformationUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MarvelApiClientImpl implements ApiClient {

    private static final String API_KEY_LABEL = "public_key_marvel";
    private static final String TS_KEY_LABEL = "ts_key_marvel";
    private static final String PRIVATE_KEY_LABEL = "private_key_marvel";

    @Override
    public List<? extends Content> findTopTitles() {
        String resourceFrom = this.getResourceFrom("http://gateway.marvel.com/v1/public/series");
        return this.toObject(resourceFrom);
    }


    private String getResourceFrom(String url) {
        try {
            ApplicationProperties applicationProperties = new ApplicationProperties();
            String apiKey = applicationProperties.getPropertiesApplication(API_KEY_LABEL);
            String ts = applicationProperties.getPropertiesApplication(TS_KEY_LABEL);
            String privateKey = applicationProperties.getPropertiesApplication(PRIVATE_KEY_LABEL);

            String hash = new DataApiInformationUtils().md5Hash(ts + privateKey + apiKey);

            String urlParams = "?ts=" + ts + "&hash=" + hash + "&apikey=" + apiKey;

            HttpClient client = HttpClient
                    .newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            HttpRequest httpRequest = HttpRequest
                    .newBuilder(new URI(url + urlParams))
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

    private List<? extends Content> toObject(String json) {
        Gson gson = new Gson();
        DataMarvelInformationStories dataMarvelInformationStories = gson.fromJson(json, DataMarvelInformationStories.class);

        dataMarvelInformationStories
                .getData()
                .getResults()
                .forEach(c -> c.setUrlImage(this.getUrlImageResourceResult(this.getResourceFrom(c.getResourceURI() + "/comics"))));

        return dataMarvelInformationStories.getData().getResults();
    }

    private String getUrlImageResourceResult(String json) {
        Gson gson = new Gson();
        DataMarvelInformationStories dataMarvelInformationStories = gson.fromJson(json, DataMarvelInformationStories.class);

        Data data = dataMarvelInformationStories.getData();
        if (!data.getResults().isEmpty()) {
            Result result = data.getResults().iterator().next();
            if (!result.getImages().isEmpty()) {
                return result.getImages().iterator().next().getPath() + "/portrait_incredible.jpg";
            }
        }

        return "";
    }

}
