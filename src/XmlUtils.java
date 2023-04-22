import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class XmlUtils {

    private static XMLInputFactory xmlIf = null;
    private static XMLStreamReader xmlR = null;
    private static XMLOutputFactory xmlOf = null;
    private static XMLStreamWriter xmlW = null;

    /**
     * Initialize XMLInputFactory and XMLStreamReader
     * @param filename xml file name
     */
    private static void initializeXMLReader(String filename) {
        try {
            xmlIf = XMLInputFactory.newInstance();
            xmlR = xmlIf.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Error in initializing XML stream reader:\n" + e.getMessage());
        }
    }

    /**
     * read people from the file Inputpersone.xml
     * @param people
     */
    public static void readPeople(ArrayList<Person> people) {

        String filename = "./InputPersone.xml";
        initializeXMLReader(filename);

        try {
            ArrayList<String> personData = new ArrayList<>();
            while (xmlR.hasNext()) {
                if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {
                    personData.add(xmlR.getText());
                }
                if (personData.size() == 5) {
                    people.add(new Person(personData));
                    personData.clear();
                }
                xmlR.next();
            }
        } catch (XMLStreamException e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }


    }

    public static void readTaxIdCodes(ArrayList<TaxIdCode> taxIdCodes) {

        String filename = "./CodiciFiscali.xml";
        String code = null;

        initializeXMLReader(filename);

        try {
            while (xmlR.hasNext()) {
                if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {
                    code = xmlR.getText();
                    taxIdCodes.add(new TaxIdCode(code));
                }
                xmlR.next();
            }
        } catch (XMLStreamException e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    public static void readCities(HashMap<String, String> cities) {

        String filename = "./Comuni.xml";
        initializeXMLReader(filename);

        try {
            while (xmlR.hasNext()) {
                if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {
                    String cityName = xmlR.getText();
                    do {
                        xmlR.next();
                        if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {
                            cities.put(cityName, xmlR.getText());
                            break;
                        }
                    } while (xmlR.hasNext());
                }
                xmlR.next();
            }
        } catch (XMLStreamException | NoSuchElementException e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    /**
     * Initialize XMLOutputFactory and XMLStreamWriter
     * @param filename xml file name
     */
    private static void initializeWriterFileXml(String filename) {
        try {
            xmlOf = XMLOutputFactory.newInstance();
            xmlW = xmlOf.createXMLStreamWriter(new FileOutputStream(filename), "utf-8");
            xmlW.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Error\n" + e.getMessage());
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    /**
     * write the final output file on xml
     * @param people read in the file InputPersone.xml
     * @param codes generated
     */

    public void writeFileXml(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {

        String filename = "./Output.xml";

        initializeWriterFileXml(filename);

        try {
            xmlW.writeStartElement("output");

            xmlW.writeStartElement("persone");
            xmlW.writeAttribute("numero", String.valueOf(people.size()));
            writePeople(people);
            xmlW.writeEndElement();

            xmlW.writeStartElement("codici");

            xmlW.writeStartElement("invalidi");
      //      xmlW.writeAttribute("id", );
            writeInvalidCodes(codes);

            xmlW.writeEndElement();

            xmlW.writeStartElement("spaiati");

            xmlW.writeEndElement();

            xmlW.writeEndElement();

            xmlW.writeEndElement();
            xmlW.writeEndDocument();
            xmlW.flush();
            xmlW.close();

        } catch (Exception e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    /** 
     * write the people in the tag 'persone'
     * @param people read in the file InputPersone.xml
     */

    private static void writePeople(ArrayList<Person> people) {

        try{

            for (int i = 0; i < people.size(); i++) {

                xmlW.writeStartElement("persona");
                xmlW.writeAttribute("id", Integer.toString(i));

                xmlW.writeStartElement("nome");
                xmlW.writeCharacters(people.get(i).getName());
                xmlW.writeEndElement();

                xmlW.writeStartElement("cognome");
                xmlW.writeCharacters(people.get(i).getSurname());
                xmlW.writeEndElement();

                xmlW.writeStartElement("sesso");
                xmlW.writeCharacters(String.valueOf(people.get(i).getSex()));
                xmlW.writeEndElement();

                xmlW.writeStartElement("comune_nascita");
                xmlW.writeCharacters(people.get(i).getMunicipality());
                xmlW.writeEndElement();

                xmlW.writeStartElement("data_nascita");
                xmlW.writeCharacters(String.valueOf(people.get(i).getBirthDate()));
                xmlW.writeEndElement();

                xmlW.writeStartElement("codice_fiscale");
                xmlW.writeCharacters(people.get(i).getTaxIdCode());
                xmlW.writeEndElement();

                xmlW.writeEndElement();
            }

        } catch (Exception e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    /**
     * write the invalid codes in the tag 'codici'
     * @param codes generated
     */

    private static void writeInvalidCodes(ArrayList<TaxIdCode> codes) {

        try {

            for (int i = 0; i < codes.size(); i++) {

                if (!codes.get(i).isValid()) {
                    continue;
                }

                xmlW.writeStartElement("codice");
                xmlW.writeCharacters(codes.get(i).getCode());
                xmlW.writeEndElement();
            }

        } catch (Exception e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }
}
