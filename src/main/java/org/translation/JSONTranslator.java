package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private final Map<String, String> translations = new HashMap<>();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // !ODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                translations.put(jsonObject.getString("key"), jsonObject.getString("value"));
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // !!DO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        List<String> langCode = new ArrayList<>();
        for (String key : translations.keySet()) {
            if (translations.get(key).equals(country)) {
                langCode.add(key);
            }
        }
        return langCode;
    }

    @Override
    public List<String> getCountries() {
        // !!DO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        return new ArrayList<>(translations.keySet());
    }

    @Override
    public String translate(String country, String language) {
        // !!DO Task: complete this method using your instance variables as needed
        // Check if the translations map contains the language code as a key
        if (translations.containsKey(language)) {
            String translation = translations.get(language);
            if (translation.equals(country)) {
                return translation;
            }
        }
        return null;
    }
}
