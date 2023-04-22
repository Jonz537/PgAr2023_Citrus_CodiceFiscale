import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    static private ArrayList<Person> people = new ArrayList<>();
    static private ArrayList<TaxIdCode> taxIdCodes = new ArrayList<>();
    static private ArrayList<TaxIdCode> readTaxIdCodes = new ArrayList<>();
    static private ArrayList<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        TaxIdCodeXml taxIdCodeXml = new TaxIdCodeXml();
        TaxIdCodeJson taxIdCodeJson = new TaxIdCodeJson();

        taxIdCodeXml.start(people, taxIdCodes, readTaxIdCodes);
    }

    /**
     * @param name String with city's name
     * @return Code of given city
     */
    public static String getCitiesByName(String name) {
        // TODO: maybe change with hashmap
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city.getCode();
            }
        }
        return null;
    }
}