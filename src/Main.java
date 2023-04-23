import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static private ArrayList<Person> people = new ArrayList<>();
    static private ArrayList<TaxIdCode> taxIdCodes = new ArrayList<>();
    static private HashMap<String, String> cities = new HashMap<>();

    public static void main(String[] args) {
        XmlMain xmlMain = new XmlMain();
        TaxIdCodeJson taxIdCodeJson = new TaxIdCodeJson();

        xmlMain.start(people, taxIdCodes, cities);
    }

    /**
     * @param name String with city's name
     * @return Code of given city
     */
    public static String getCitiesByName(String name) {
        return cities.getOrDefault(name, null);
    }


    public static boolean isContained(String taxIdCode) {
        return taxIdCodes.contains(new TaxIdCode(taxIdCode));
    }
}