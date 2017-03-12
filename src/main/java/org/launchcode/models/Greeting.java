package org.launchcode.models;

import java.util.*;

/**
 * Created by laura on 3/7/2017.
 */


public class Greeting {
    public static HashMap<String, String> languages(){
        HashMap<String, String> allLanguages = new HashMap<>();
        allLanguages.put("French", "Bonjour ");
        allLanguages.put("German", "Gutten tag ");
        allLanguages.put("Pig Latin", "Ellohay ");
        allLanguages.put("English", "Hello ");
        allLanguages.put("Spanish", "Hola ");
        allLanguages.put("Mandarin", "Ni hau");

        return allLanguages;
    }

    public static String createMessage(String language) {
        return languages().get(language);
    }

    public static String getLanguages(){
        List languages = new ArrayList(languages().keySet());

        Collections.sort(languages);
        String selectCriteria = "<select name='language'>";

        for (int i = 0; i < languages.size(); i++) {
            selectCriteria += "<option>" + languages.get(i) + "</option>";
            }

        selectCriteria += "</select>";
        return selectCriteria;
    }
}
