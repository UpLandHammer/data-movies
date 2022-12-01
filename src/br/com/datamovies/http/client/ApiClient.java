package br.com.datamovies.http.client;

import br.com.datamovies.models.Content;

import java.util.List;

public interface ApiClient {

    List<? extends Content> findTopTitles();

}
