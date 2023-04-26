package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {

    private String name;
    private String surname;
    private Calendar birthdate; //gaySpawned
    private Sex sex;
    private String birthCity;
    private String taxIdCode;

    public Person(ArrayList<String> personData) {
        this.name = personData.get(0);
        this.surname = personData.get(1);
        this.sex = Sex.valueOf(personData.get(2));
        this.birthCity = personData.get(3);

        String[] date = personData.get(4).split("-");
        this.birthdate = new GregorianCalendar(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[2]));
        this.taxIdCode = new TaxIdCode(surname, name, birthdate, sex, birthCity).getCode();
    }

    public Person(String name, String surname, Calendar birthdate, Sex sex, String birthCity) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.sex = sex;
        this.birthCity = birthCity;
        this.taxIdCode = new TaxIdCode(surname, name, birthdate, sex, birthCity).getCode();
    }

    /**
     *  check if taxIdCode is contained in CodiciFiscali
     */
    public void isContainedInCodes() {
        if (!Main.isContained(taxIdCode)) {
            taxIdCode = "ASSENTE";
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public Sex getSex() {
        return sex;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public String getTaxIdCode() {
        return taxIdCode;
    }

    public String getStringDate() {
        return birthdate.get(Calendar.YEAR)+ "-" + String.format("%02d", (birthdate.get(Calendar.MONTH) + 1)) + "-" + birthdate.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String toString() {
        return "main.Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthdate.get(Calendar.WEEK_OF_YEAR) + "/" + birthdate.get(Calendar.MONTH) +
                "/" + birthdate.get(Calendar.YEAR) +
                ", sex=" + sex +
                ", city='" + birthCity + '\'' +
                ", taxIdCode='" + taxIdCode + '\'' +
                '}';
    }

    /**
     * check if the taxIdCode is valid
     * @return taxIdCode if is valid however return ASSENTE
     */
    public String getTaxIdCodeIfValid() {
        return (Main.isContained(taxIdCode))? taxIdCode : "ASSENTE";
    }
}
