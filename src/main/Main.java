package main;

import unibs.MenuManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static private ArrayList<Person> people = new ArrayList<>();
    static private ArrayList<TaxIdCode> taxIdCodes = new ArrayList<>();
    static private HashMap<String, String> cities = new HashMap<>();

    static private MenuManager menuManager = new MenuManager("What format you want the program to read/write?", new String[]{
            "XML", "JSON", "XML and JSON"
    });

    public static void main(String[] args) {
        JsonUtils jsonUtils = new JsonUtils();
        switch (menuManager.choose()) {
            case 0 -> {
                XmlUtils.readFile(people, taxIdCodes, cities);
                XmlUtils.writeFile(people, taxIdCodes);

            }
            case 1 -> {
                JsonUtils.readFile(people, taxIdCodes, cities);
                JsonUtils.writeFile(people, taxIdCodes);
            }
            case 2 -> {
                XmlUtils.readFile(people, taxIdCodes, cities);
                XmlUtils.writeFile(people, taxIdCodes);
                clearAll();
                JsonUtils.readFile(people, taxIdCodes, cities);
                JsonUtils.writeFile(people, taxIdCodes);
            }

            default -> System.out.println("Too bad!");
        }
    }

    /**
     * @param name String with city's name
     * @return Code of given city
     */
    public static String getCitiesByName(String name) {
        return cities.getOrDefault(name, null);
    }

    public static boolean isContained(String taxIdCode) {
        return taxIdCodes.remove(new TaxIdCode(taxIdCode));
    }

    private static void clearAll() {
        people.clear();
        taxIdCodes.clear();
        cities.clear();
    }
}