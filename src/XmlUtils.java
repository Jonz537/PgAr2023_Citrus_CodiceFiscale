import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XmlUtils {

    XMLInputFactory xmlIf = null;
    XMLStreamReader xmlR = null;
    XMLOutputFactory xmlOf = null;
    XMLStreamWriter xmlW = null;

//    public void readFileXml(String filename) {
//
//        try {
//            xmlIf = XMLInputFactory.newInstance();
//            xmlR = xmlIf.createXMLStreamReader(filename, new FileInputStream(filename));
//        } catch (Exception e) {
//            System.out.println("Errore nell'inizializzazione del reader:");
//            System.out.println(e.getMessage());
//        }
//
//        while (xmlR.hasNext()) {
//            switch (xmlR.getEventType()) {
//                case XMLStreamConstants.START_DOCUMENT:
//                    System.out.println("Start Read Doc " + filename); break;
//                case XMLStreamConstants.START_ELEMENT:
//                    System.out.println("Tag " + xmlR.getLocalName());
//                    for (int i = 0; i < xmlR.getAttributeCount(); i++)
//                        System.out.printf(" => attributo %s->%s%n", xmlR.getAttributeLocalName(i), xmlR.getAttributeValue(i));
//                    break;
//                case XMLStreamConstants.END_ELEMENT:
//                    System.out.println("END-Tag " + xmlR.getLocalName()); break;
//                case XMLStreamConstants.COMMENT:
//                    System.out.println("// commento " + xmlR.getText()); break;
//                case XMLStreamConstants.CHARACTERS:
//                    if (xmlR.getText().trim().length() > 0)
//                        System.out.println("-> " + xmlR.getText());
//                    break;
//            }
//            xmlR.next();
//        }
//    }
//
//    public void writeFileXml(String filename, String[] data) {
//
//        try {
//            xmlOf = XMLOutputFactory.newInstance();
//            xmlW = xmlOf.createXMLStreamWriter(new FileOutputStream(filename), "utf-8");
//            xmlW.writeStartDocument("utf-8", "1.0");
//        } catch (Exception e) {
//            System.out.println("Errore nell'inizializzazione del writer:");
//            System.out.println(e.getMessage());
//        }
//
//        try { // blocco try per raccogliere eccezioni
//            xmlW.writeStartElement("programmaArnaldo");
//            xmlW.writeComment("INIZIO LISTA");
//            for (int i = 0; i < data.length; i++) {
//                xmlW.writeStartElement("autore");
//                xmlW.writeAttribute("id", Integer.toString(i));
//                xmlW.writeCharacters(data[i]);
//                xmlW.writeEndElement();
//            }
//            xmlW.writeEndElement();
//            xmlW.writeEndDocument();
//            xmlW.flush();
//            xmlW.close();
//        } catch (Exception e) {
//            System.out.println("Errore nella scrittura");
//        }
//    }
}
