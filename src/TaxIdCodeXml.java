import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaxIdCodeXml {

    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<TaxIdCode> taxIdCodes = new ArrayList<>();
    public static ArrayList<City> cities = new ArrayList<>();

    public static void xml() {
        cities.add(new City("Sesso", "B157"));
        TaxIdCode taxIdCode = new TaxIdCode("Giorgio", "Saleri", new GregorianCalendar(2003, Calendar.MAY, 29),
                Sex.MALE, "Sesso");
        System.out.println(taxIdCode);

    }

    public static String getMunicipalityCodeByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city.getCode();
            }
        }
        return null;
    }
}
