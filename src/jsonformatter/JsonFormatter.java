package jsonformatter;

import main.Person;
import main.TaxIdCode;

import java.util.ArrayList;

public class JsonFormatter {

    ArrayList<Persona> persone = new ArrayList<>();
    ListaCodici codici = new ListaCodici();

    public JsonFormatter(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {
        for (Person person: people) {
            persone.add(new Persona(person));
        }
        for (TaxIdCode code: codes) {
            codici.addCode(code);
        }
    }
}




