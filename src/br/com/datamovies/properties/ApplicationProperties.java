package br.com.datamovies.properties;

import br.com.datamovies.exceptions.ApplicationPropertiesException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationProperties {

    public static final String PROPERTIES_SOURCE = "/properties.txt";

    public String getPropertiesApplication(String propertyName) {

        List<String> properties = readProperties();

        String[] propertyArray = properties.stream()
                .filter(p -> p.contains(propertyName))
                .findFirst()
                .map(p -> p.split(":"))
                .orElseThrow(() -> new ApplicationPropertiesException("Property not found"));

        return propertyArray[1];
    }

    private List<String> readProperties() {
        try {

            List<String> lines = new ArrayList<>();

            InputStream resourceAsStream = getClass().getResourceAsStream(PROPERTIES_SOURCE);

            Reader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (line != null) {
                lines.add(line);
                stringBuilder.append(line + "\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            return lines;
        } catch (IOException e) {
            throw new ApplicationPropertiesException("Fail to read properties", e);
        }
    }
}
