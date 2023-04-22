import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class XmlUtils {

    private static XMLInputFactory xmlIf = null;
    private static XMLStreamReader xmlR = null;
    private XMLOutputFactory xmlOf = null;
    private XMLStreamWriter xmlW = null;


    /**
     * Initialize XMLInputFactory and XMLSreamReader
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

    public static void readTaxIdCodesXml(ArrayList<TaxIdCode> readTaxIdCodes) {

        String filename = "./CodiciFiscali.xml";
        String code = null;

        initializeXMLReader(filename);

        try {
            while (xmlR.hasNext()) {
                if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {
                    code = xmlR.getText();
                    readTaxIdCodes.add(new TaxIdCode(code));
                }
                xmlR.next();
            }
        } catch (XMLStreamException e) {
            System.out.println("Reading error:\n" + e.getMessage());
        }
    }

    public static void readCitiesXml(HashMap<String, String> cities) {

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

    public void readFileXml(String filename) {

        try {
            xmlIf = XMLInputFactory.newInstance();
            xmlR = xmlIf.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                if (!xmlR.hasNext()) break;
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            switch (xmlR.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("Start Read Doc " + filename); break;
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println("Tag " + xmlR.getLocalName());
                    for (int i = 0; i < xmlR.getAttributeCount(); i++)
                        System.out.printf(" => attributo %s->%s%n", xmlR.getAttributeLocalName(i), xmlR.getAttributeValue(i));
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("END-Tag " + xmlR.getLocalName()); break;
                case XMLStreamConstants.COMMENT:
                    System.out.println("// commento " + xmlR.getText()); break;
                case XMLStreamConstants.CHARACTERS:
                    if (xmlR.getText().trim().length() > 0)
                        System.out.println("-> " + xmlR.getText());
                    break;
            }
            try {
                xmlR.next();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void writeFileXml(String filename, Person[] people, TaxIdCode[] codes) {

        try {
            xmlOf = XMLOutputFactory.newInstance();
            xmlW = xmlOf.createXMLStreamWriter(new FileOutputStream(filename), "utf-8");
            xmlW.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

        try {
            xmlW.writeStartElement("output");
            xmlW.writeStartElement("persone");
       //     xmlW.writeAttribute("id", );
            for (Person person : people) {

                xmlW.writeStartElement("persona");
                xmlW.writeAttribute("number", String.valueOf(people.length));

                xmlW.writeStartElement("nome");
                xmlW.writeCharacters(person.getName());
                xmlW.writeEndElement();

                xmlW.writeStartElement("cognome");
                xmlW.writeCharacters(person.getSurname());
                xmlW.writeEndElement();

                xmlW.writeStartElement("sesso");
                xmlW.writeCharacters(String.valueOf(person.getSex()));
                xmlW.writeEndElement();

                xmlW.writeStartElement("comune_nascita");
                xmlW.writeCharacters(person.getMunicipality());
                xmlW.writeEndElement();

                xmlW.writeStartElement("data_nascita");
                xmlW.writeCharacters(String.valueOf(person.getBirthDate()));
                xmlW.writeEndElement();

                xmlW.writeStartElement("codice_fiscale");
                xmlW.writeCharacters(person.getTaxIdCode());
                xmlW.writeEndElement();

                xmlW.writeEndElement();
            }

            xmlW.writeEndElement();
            xmlW.writeStartElement("codici");

            xmlW.writeStartElement("invalidi");
      //      xmlW.writeAttribute("id", );
            for (int i = 0; i < codes.length; i++) {

                if (!codes[i].isValid()) {
                    continue;
                }

                xmlW.writeStartElement("codice");
//                xmlW.writeCharacters();
                xmlW.writeEndElement();
            }
            xmlW.writeEndElement();

            xmlW.writeEndElement();
            xmlW.writeEndElement();
            xmlW.writeEndDocument();
            xmlW.flush();
            xmlW.close();
        } catch (Exception e) {
            System.out.println("Errore nella scrittura");
        }
    }
}
