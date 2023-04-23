import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private static FileReader fileReader;
    private static Gson gson;
    private static Type type;

    public static void readFile(ArrayList<Person> people, ArrayList<TaxIdCode> taxIdCodes, HashMap<String, String> cities) {
        readCities(cities);
        readTaxIdCodes(taxIdCodes);
        readPeople(people);
    }

    private static void initializeJsonReader(String filename) {
        try {
            fileReader = new FileReader(filename);
            gson = new Gson();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found:\n" + e.getMessage());
        }
    }


    public static void readCities(HashMap<String, String> cities) {
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

    public static void readTaxIdCodes(ArrayList<TaxIdCode> taxIdCodes) {
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

    public static void readPeople(ArrayList<Person> people) {
        String filePath = "./InputPersone.json";
        initializeJsonReader(filePath);

        type = new TypeToken<ArrayList<Person>>(){}.getType();

        ArrayList<Person> listToParse = gson.fromJson(fileReader, type);

        for (Person person: listToParse) {
            System.out.println(person);
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }

    public static void writeFile(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {

    }
}