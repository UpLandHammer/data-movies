package br.com.datamovies;

import br.com.datamovies.properties.ApplicationProperties;
import br.com.datamovies.services.DataMovieInformation;

public class Application {

    public static void main(String[] args) {
        System.out.println("Data Movies");

        ApplicationProperties applicationProperties = new ApplicationProperties();
        String propertiesApplication = applicationProperties.getPropertiesApplication("api_key");
        System.out.println(propertiesApplication);

        String topMovies = new DataMovieInformation().getTopMovies("https://imdb-api.com/API/Top250Movies/");

        System.out.println(topMovies);

    }
}
