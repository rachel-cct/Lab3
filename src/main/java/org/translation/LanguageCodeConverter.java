package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    // !!DO Task: pick appropriate instance variables to store the data necessary for this class
    // map to store original term as key, its translation as value
    private final Map<String, String> countrytocode = new HashMap<>();
    // map storing code as key, country as value
    private final Map<String, String> codetocountry = new HashMap<>();
    private int count;

    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // !!DO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()
            // declares variable "line" of type String
            // each iteration: @line@ will hold current element (an object from array of languages)
            for (String line : lines) {
                String[] parts = line.split("\t");
                // parts is a list of string, each with parts[0] and parts[1]
                this.countrytocode.put(parts[0], parts[1]);
                this.codetocountry.put(parts[1], parts[0]);
                count++;
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // !!DO Task: update this code to use your instance variable to return the correct value
        return codetocountry.get(code);
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // !!DO Task: update this code to use your instance variable to return the correct value
        return countrytocode.get(language);
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // !!DO Task: update this code to use your instance variable to return the correct value
        return count;
    }
}
