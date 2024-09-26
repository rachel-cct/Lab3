package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A minimal example of reading and using the JSON data from resources/sample.json.
 */
public class JSONTranslationExample {

    public static final int CANADA_INDEX = 30;
    private final JSONArray jsonArray;

    // Note: CheckStyle is configured so that we are allowed to omit javadoc for constructors
    public JSONTranslationExample() {
        try {
            // this next line of code reads in a file from the resources folder as a String,
            // which we then create a new JSONArray object from.
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource("sample.json")
                    .toURI()));
            this.jsonArray = new JSONArray(jsonString);
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the Spanish translation of Canada.
     *
     * @return the Spanish translation of Canada
     */
    public String getCanadaCountryNameSpanishTranslation() {
        JSONObject canada = jsonArray.getJSONObject(CANADA_INDEX);
        return canada.getString("es");
    }

    /**
     * Returns the name of the country based on the provided country and language codes.
     *
     * @param countryCode  the country, as its three-letter code.
     * @param languageCode the language to translate to, as its two-letter code.
     * @return the translation of country to the given language or "Country not found" if there is no translation.
     */
    public String getCountryNameTranslation(String countryCode, String languageCode) {
        // Default result
        String result = "Country not found";
        try {
            // Load the JSON file using Files.readString
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource("sample.json")
                    .toURI()));
            JSONObject jsonData = new JSONObject(jsonString);

            // Check if the country code exists in the JSON data
            if (jsonData != null && jsonData.has(countryCode)) {
                JSONObject countryData = jsonData.getJSONObject(countryCode);

                // Check if the language translation exists
                if (countryData.has(languageCode)) {
                    // Update the result if the translation is found
                    result = countryData.getString(languageCode);
                }
            }
        }
        // Handle any exceptions while reading the file
        catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        // Return the result at the end
        return result;
    }

    /**
     * Prints the Spanish translation of Canada.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        JSONTranslationExample jsonTranslationExample = new JSONTranslationExample();

        System.out.println(jsonTranslationExample.getCanadaCountryNameSpanishTranslation());
        String translation = jsonTranslationExample.getCountryNameTranslation("can", "es");
        System.out.println(translation);
    }
}
