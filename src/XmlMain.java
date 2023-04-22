import java.util.*;

public class XmlMain {


    public void start(ArrayList<Person> people, ArrayList<TaxIdCode> taxIdCodes,
                      ArrayList<TaxIdCode> readTaxIdCodes, HashMap<String, String> cities) {

        // Start reading XML files
        XmlUtils.readCitiesXml(cities);
        XmlUtils.readTaxIdCodesXml(readTaxIdCodes);
    }
}
