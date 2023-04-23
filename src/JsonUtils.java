import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
            fileReader = new FileReader(new File(filename));
            gson = new Gson();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found:\n" + e.getMessage());
        }
    }


    public static void readCities(HashMap<String, String> cities) {

    }

    public static void readTaxIdCodes(ArrayList<TaxIdCode> taxIdCodes) {
        String filePath = "./CodiciFiscali.json";
        initializeJsonReader(filePath);
        type = new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> listToParse = gson.fromJson(fileReader, type);

        for (String s: listToParse) {
            System.out.println(s);
        }


        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error in closing JSon file reader:\n" + e.getMessage());
        }
    }

    public static void readPeople(ArrayList<Person> people) {

    }

    public static void writeFile(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {

    }

}
