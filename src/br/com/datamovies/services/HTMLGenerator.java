package br.com.datamovies.services;

import br.com.datamovies.exceptions.DataMovieInformationException;
import br.com.datamovies.http.client.Content;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private PrintWriter printWriter;

    public void generateHtmlPageMovies(List<? extends Content> contents) {
        try {
            this.printWriter = new PrintWriter("src/movie_list.html");
            this.generate(contents);
            printWriter.close();
        } catch (FileNotFoundException ex) {
            throw new DataMovieInformationException("Fail to generate html page", ex);
        }
    }


    public void generate(List<? extends Content> contents) {

        StringBuilder pageContent = new StringBuilder();

        pageContent.append("<!DOCTYPE html lang=\"en\">");

        String head = "<head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "        <title>Movies List</title>\n" +
                "    </head>";

        pageContent.append(head);

        pageContent.append("<body>");

        pageContent.append("<div class=\"container\">");

        pageContent.append("<h1>Movies List</h1>");

        pageContent.append("<div class=\"row\">");

        for (Content content : contents) {
            pageContent.append("<div class=\"card text-bg-secondary mb-3\" style=\"width: 10rem;\">\n" +
                    "            <img src=\"" + content.getUrlImage() + "\" class=\"card-img-top\" alt=\""+ content.getTitle() +"\">\n" +
                    "            <div class=\"card-body\">\n" +
                    "                <h5 class=\"card-title\">" + content.getTitle() + "</h5>\n" +
                    "                <p class=\"card-text\">Nota " + content.getRating() + " - Ano: " + content.getYear() + "</p>\n" +
                    "            </div>\n" +
                    "        </div>");
        }

        pageContent.append("</div>");
        pageContent.append("</div>");

        pageContent.append("</body>");
        pageContent.append("</html>");
        this.printWriter.println(pageContent);
    }
}
