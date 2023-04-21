import java.util.Calendar;
import java.util.Date;

public class Person {

    private String name;
    private String surname;
    private Calendar birthDate; //gaySpawned
    private Sex sex;
    private Municipality municipality;
    private String taxIdCode;

    public Person(String name, String surname, Calendar birthDate, Sex sex, Municipality municipality) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.municipality = municipality;
    }

}
