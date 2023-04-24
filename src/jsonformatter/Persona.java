package jsonformatter;

import main.Person;

public class Persona {

    String nome;
    String cognome;
    String sesso;
    String comune_nascita;
    String data_nascita;
    String codice_fiscale;

    public Persona(Person person) {
        this.nome = person.getName();
        this.cognome = person.getSurname();
        this.sesso = person.getSex().toString();
        this.comune_nascita = person.getBirthCity();
        this.data_nascita = person.getStringDate();
        this.codice_fiscale = person.getTaxIdCodeIfValid();
    }
}
