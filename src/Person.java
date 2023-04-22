import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {

    private String name;
    private String surname;
    private Calendar birthDate; //gaySpawned
    private Sex sex;
    private String city;
    private String taxIdCode;

    public Person(ArrayList<String> personData) {
        this.name = personData.get(0);
        this.surname = personData.get(1);
        this.sex = Sex.valueOf(personData.get(2));
        this.city = personData.get(3);

        String[] date = personData.get(4).split("-");
        this.birthDate = new GregorianCalendar(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[2]));
        this.taxIdCode = new TaxIdCode(surname, name, birthDate, sex, city).getCode();
    }

    public Person(String name, String surname, Calendar birthDate, Sex sex, String city) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.city = city;
        this.taxIdCode = new TaxIdCode(surname, name, birthDate, sex, city).getCode();
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
        return city;
    }

    public String getTaxIdCode() {
        return taxIdCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate.get(Calendar.WEEK_OF_YEAR) + "/" + birthDate.get(Calendar.MONTH) +
                "/" + birthDate.get(Calendar.YEAR) +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", taxIdCode='" + taxIdCode + '\'' +
                '}';
    }
}
