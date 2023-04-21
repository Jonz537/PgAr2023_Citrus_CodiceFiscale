import java.util.Date;

public class Person {

    private String name;
    private String surname;
    private Date birthDate; //gaySpawned
    private Sex sex;
    private Municipality municipality;
    private String taxIdCode;

    public Person(String name, String surname, Date birthDate, Sex sex, Municipality municipality) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.municipality = municipality;
    }

}
