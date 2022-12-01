# data-movies

###### #7DaysOfCode

Simple java project to get list of movies from [IMDB Api](https://imdb-api.com/API) and [Marvel Api](https://developer.marvel.com/docs)

This project get all 250 top movies.

It is idealized by the [Alura](https://www.alura.com.br) school, who challenges the student is [Paulo Silveira](https://twitter.com/paulo_caelum?s=20&t=rdXt3W7TSM1JxhEGDpmSeQ) CEO of the Alura group. A great idea!

### How to use?

Step 1: Download this project

Step 2: 
    - Create a file with name "properties.txt" on /src folder 
    - Put a string with key created on [IMDB Api](https://imdb-api.com/API) on this format: "api_key:your-key", because the application use this for Http Request on Imdb Api.
    - Put a string with public key, private key and ts(timestamp) both created on [Marvel Api](https://developer.marvel.com/docs) on this format: 
        * public_key_marvel:{content}
        * private_key_marvel:{content}
        * ts_key_marvel:{content}

Step 3: Execute on your IDE or compile yourself and use on command line.

The expected output is a string like this in #7DaysOfCode - Java 1/7:

```
nov 25, 2022 11:11:46 PM br.com.datamovies.Application main
INFO: Data Movies
nov 25, 2022 11:11:48 PM br.com.datamovies.Application main
INFO: {"items":[{"id":"tt0111161","rank":"1","title":"The Shawshank Redemption","fullTitle":"The Shawshank Redemption ....
```

The expected output is a string like this in #7DaysOfCode - Java 2/7:

```
nov 26, 2022 12:25:14 AM br.com.datamovies.Application main
INFO: Data Movies 
nov 26, 2022 12:25:16 AM br.com.datamovies.Application main
INFO: Movies title list.
nov 26, 2022 12:25:16 AM br.com.datamovies.Application printLogger
INFO: The Shawshank Redemption...
```

The expected output after 7 day of code is a string below...

```
nov 30, 2022 10:38:22 PM br.com.datamovies.Application main
INFO: Data Movies and Marvel Comics  html page generator.
nov 30, 2022 10:38:38 PM br.com.datamovies.Application main
INFO: Html page generated.
 
```


I used only Java Code.

Thank You for read this!
