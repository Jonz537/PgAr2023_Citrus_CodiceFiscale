package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jsonformatter.JsonFormatter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonUtils {

    private static FileReader fileReader;
    private static FileWriter fileWriter;
    private static Gson gson;
    private static Type type;

    /**
     * read data from Comuni, CodiciFiscali and InputPersone file json
     * @param people read from InputPersone
     * @param taxIdCodes read from CodiciFiscali
     * @param cities read from Comuni
     */
    public static void readFile(ArrayList<Person> people, ArrayList<TaxIdCode> taxIdCodes, HashMap<String, String> cities) {
        readCities(cities);
        readTaxIdCodes(taxIdCodes);
        readPeople(people);
    }

    /**
     * Initialize FileReader and Gson
     * @param filename json file name
     */
    private static void initializeJsonReader(String filename) {
        try {
            fileReader = new FileReader(filename);
            gson = new Gson();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found:\n" + e.getMessage());
        }
    }

    /**
     * read cities from Comuni.json
     * @param cities save input in this map
     */
    private static void readCities(HashMap<String, String> cities) {
        String filePath = "./Comuni.json";
        initializeJsonReader(filePath);

        type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();

        ArrayList<HashMap<String, String>> listToParse = gson.fromJson(fileReader, type);

        for (HashMap<String, String> entry: listToParse) {
            cities.put(entry.get("nome"), entry.get("codice"));
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }

    /**
     * read taxIdCodes from CodiciFiscali.json
     * @param taxIdCodes save input in this list
     */
    private static void readTaxIdCodes(ArrayList<TaxIdCode> taxIdCodes) {
        String filePath = "./CodiciFiscali.json";
        initializeJsonReader(filePath);

        type = new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> listToParse = gson.fromJson(fileReader, type);

        for (String code: listToParse) {
            taxIdCodes.add(new TaxIdCode(code));
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }

    /**
     * read people from InputPersone.json
     * @param people save input in this list
     */
    private static void readPeople(ArrayList<Person> people) {
        String filePath = "./InputPersone.json";
        initializeJsonReader(filePath);

        type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();

        ArrayList<HashMap<String, String>> listToParse = gson.fromJson(fileReader, type);

        for (HashMap<String, String> person: listToParse) {
            ArrayList<String> personData = new ArrayList<>();
            personData.add(person.get("nome"));
            personData.add(person.get("cognome"));
            personData.add(person.get("sesso"));
            personData.add(person.get("comune_nascita"));
            personData.add(person.get("data_nascita"));
            people.add(new Person(personData));
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }

    /**
     * initialize json writer
     * @param filePath path of the file
     */
    private static void initializeJsonWriter(String filePath) {
        try {
            fileWriter = new FileWriter(filePath);
            gson = new GsonBuilder().setPrettyPrinting().create();
        } catch (IOException e) {
            System.out.println("Error: file not found:\n" + e.getMessage());
        }
    }

    /**
     * write people and codes in the output.json file
     * @param people read in InputPersone.json
     * @param codes read in CodiciFiscali.json
     */
    public static void writeFile(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {
        String filePath = "./Output.json";
        initializeJsonWriter(filePath);

        JsonFormatter jsonFormatter = new JsonFormatter(people, codes);
        gson.toJson(jsonFormatter, fileWriter);

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }
}
