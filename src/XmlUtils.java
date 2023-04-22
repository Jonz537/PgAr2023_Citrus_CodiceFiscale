import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XmlUtils {

    static XMLInputFactory xmlIf = null;
    static XMLStreamReader xmlR = null;
    XMLOutputFactory xmlOf = null;
    XMLStreamWriter xmlW = null;

    public static void readTaxIdCodesXml(ArrayList<TaxIdCode> readTaxIdCodes) {

        String filename = "./CodiciFiscali.xml";
        String code = null;

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
            } catch (Exception e) {
                throw new RuntimeException(e);
            } // continua a leggere finché ha eventi a disposizione
            //switch (xmlR.getEventType()) { // switch sul tipo di evento
                //case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    //System.out.println("Start Read Doc " + filename);
                //    break;
                //case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                    //System.out.println("Tag " + xmlR.getLocalName());
                    //for (int i = 0; i < xmlR.getAttributeCount(); i++)
                     //   System.out.printf(" => attributo %s->%s%n", xmlR.getAttributeLocalName(i), xmlR.getAttributeValue(i));
                //    break;
                //case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
                    //System.out.println("END-Tag " + xmlR.getLocalName());
                //  break;
                //case XMLStreamConstants.COMMENT:
                    //System.out.println("// commento " + xmlR.getText());
                  //  break; // commento: ne stampa il contenuto
                //case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                    if (xmlR.getEventType() == XMLStreamConstants.CHARACTERS && xmlR.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
                        //System.out.println("-> " + xmlR.getText());
                        code = xmlR.getText();
                        readTaxIdCodes.add(new TaxIdCode(code));
                    }

                    //break;
            //}
            try {
                xmlR.next();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }

        //for (int i = 0; i < Main.readTaxIdCodes.size(); i++) {
        //    System.out.println(Main.readTaxIdCodes.get(i).toString());
        //}
    }

    public void readCitiesXml() {
        String filename = "./Comuni.xml";
        City city = null;

        try {
            xmlIf = XMLInputFactory.newInstance();
            xmlR = xmlIf.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 1; i++) {
            
        }

        while (true) {
            try {
                if (!xmlR.hasNext()) break;
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }

            switch (xmlR.getEventType()) {
                //case XMLStreamConstants.START_DOCUMENT:
                //    System.out.println("Start Read Doc " + filename); break;
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println("Tag " + xmlR.getLocalName());
                    for (int i = 0; i < xmlR.getAttributeCount(); i++)
                        System.out.printf(" => attributo %s->%s%n", xmlR.getAttributeLocalName(i), xmlR.getAttributeValue(i));
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("END-Tag " + xmlR.getLocalName()); break;
                //case XMLStreamConstants.COMMENT:
                //    System.out.println("// commento " + xmlR.getText()); break;
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
