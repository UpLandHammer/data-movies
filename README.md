# data-movies

Simple java project to get list of movies from [IMDB Api](https://imdb-api.com/API).

This project get all 250 top movies.

How to use?

Step 1: Download this project

Step 2: Create a file with name "properties.txt" on /src folder and put a string with key created on [IMDB Api](https://imdb-api.com/API) on this format: "api_key:your-key", because the application use this for Http Request on Imdb Api.

Step 3: Execute on your IDE or compile yourself and use on command line.

The expected output is a string like this:

```
nov 25, 2022 11:11:46 PM br.com.datamovies.Application main
INFO: Data Movies
nov 25, 2022 11:11:48 PM br.com.datamovies.Application main
INFO: {"items":[{"id":"tt0111161","rank":"1","title":"The Shawshank Redemption","fullTitle":"The Shawshank Redemption ....
```

I used only Java Code.

Thank You for read this!
