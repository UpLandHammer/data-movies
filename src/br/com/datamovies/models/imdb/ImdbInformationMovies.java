package br.com.datamovies.models.imdb;

import java.util.List;

public class ImdbInformationMovies {

    private List<Movie> items;
    private String errorMessage;

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
