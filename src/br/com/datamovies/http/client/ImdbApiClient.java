package br.com.datamovies.http.client;

import br.com.datamovies.models.Movie;

import java.util.List;

public interface ImdbApiClient {

    List<Movie> findTopMovies();

}
