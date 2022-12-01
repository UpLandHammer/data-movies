package br.com.datamovies.models.imdb;

import br.com.datamovies.models.Content;

public class Movie implements Content, Comparable<Content> {

    private String id;
    private String rank;
    private String title;
    private String fullTitle;
    private String year;
    private String image;
    private String crew;
    private String imDbRating;
    private String imDbRatingCount;


    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrlImage() {
        return image;
    }

    @Override
    public String getRating() {
        return imDbRating;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getType() {
        return "Movie";
    }

    public boolean hasContent() {
        return title != null && !title.isEmpty() ||
               image != null && !image.isEmpty();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", urlImage='" + image + '\'' +
                ", rating='" + image + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int compareTo(Content o) {
        return o.getTitle().compareTo(o.getTitle());
    }
}
