import java.util.Calendar;

public class Person {

    private String name;
    private String surname;
    private Calendar birthDate; //gaySpawned
    private Sex sex;
    private String municipality;
    private String taxIdCode;

    public Person(String name, String surname, Calendar birthDate, Sex sex, String municipality) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.municipality = municipality;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getTaxIdCode() {
        return taxIdCode;
    }
}
