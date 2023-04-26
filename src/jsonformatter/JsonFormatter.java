package jsonformatter;

import main.Person;
import main.TaxIdCode;

import java.util.ArrayList;

public class JsonFormatter {

    ArrayList<Persona> persone = new ArrayList<>();
    ListaCodici codici = new ListaCodici();

    /**
     * save people and codes for the final output file (json version)
     * @param people saved in this list
     * @param codes saved in this array
     */
    public JsonFormatter(ArrayList<Person> people, ArrayList<TaxIdCode> codes) {
        for (Person person: people) {
            persone.add(new Persona(person));
        }
        for (TaxIdCode code: codes) {
            codici.addCode(code);
        }
    }
}




