package br.com.datamovies.models;

public class Movie {

    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean hasContent() {
        return title != null && !title.isEmpty() ||
               urlImage != null && !urlImage.isEmpty();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", rating='" + rating + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
