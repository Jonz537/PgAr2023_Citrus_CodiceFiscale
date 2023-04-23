import java.util.*;

public class XmlMain {


    public void start(ArrayList<Person> people, ArrayList<TaxIdCode> taxIdCodes, HashMap<String, String> cities) {

        // Start reading XML files
        XmlUtils.readCities(cities);
        XmlUtils.readTaxIdCodes(taxIdCodes);
        XmlUtils.readPeople(people);

        XmlUtils.writeFileXml(people, taxIdCodes);

    }
}
