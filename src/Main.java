import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    static ArrayList<TaxIdCode> readTaxIdCodes = new ArrayList<>(); 
    
    public static void main(String[] args) {
//        XmlUtils.readTaxIdCodesXml();
        TaxIdCodeXml.xml();
    }
}