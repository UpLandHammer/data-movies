package br.com.datamovies.http.client;

import java.util.List;

public interface ApiClient {

    List<? extends Content> findTopTitles();

}
