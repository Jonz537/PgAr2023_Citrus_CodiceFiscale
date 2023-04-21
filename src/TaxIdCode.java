import java.util.*;

public class TaxIdCode {
    private String code;
    private final static HashMap<Character, Integer> monthMap = new HashMap<>(Map.of('B', 28,
            'D', 30, 'H', 30,'P', 30, 'S', 30));
    private final static ArrayList<Character> monthMapReverse = (ArrayList<Character>) Arrays.asList(
        'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T');

    public TaxIdCode(String name, String surname, Calendar date, Sex sex) {
        StringBuilder generatedCode = new StringBuilder();
        // Adding name and surname chars
        generatedCode.append(nameChar(name));
        generatedCode.append(nameChar(surname));

        // Adding birth year and month
        generatedCode.append(date.get(Calendar.YEAR) % 100);
        generatedCode.append(monthMapReverse.get(date.get(Calendar.MONTH)));

        // Adding birthday and sex
        if (sex.equals(Sex.MALE)) {
            generatedCode.append(date.get(Calendar.DAY_OF_MONTH));
        } else {
            generatedCode.append(date.get(Calendar.DAY_OF_MONTH) + 40);
        }

        this.code = generatedCode.toString();
    }

    /**
     * Give the first 3 + 3 characters for the tax Id Code
     * @param name string with either name or surname
     * @return The generation of the name/surname of taxIdCode
     */
    private String nameChar(String name) {
        StringBuilder characters = new StringBuilder();
        name = name.toUpperCase();

        // Adding consonants and returning if length > 3
        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i + 1).matches("A-Z&&[^AEIOU]")) {
                characters.append(name.charAt(i));
            }
            if (characters.length() > 2) {
                return characters.toString();
            }
        }

        // Adding vowels and returning if length > 3
        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i + 1).matches("[AEIOU]")) {
                characters.append(name.charAt(i));
            }
            if (characters.length() > 2) {
                return characters.toString();
            }
        }

        // Adding "X"s if initial string isn't long enough
        while(characters.length() < 3) {
            characters.append("X");
        }

        return characters.toString();
    }

    public boolean isValid() {

        // Check characters in right positions and month characters
        if (!(code.substring(0, 6).matches("[A-Z]+") && code.substring(8, 9).matches("[ABCDEHLMPRST]") &&
                code.substring(11, 12).matches("[A-Z]+") && code.substring(15, 16).matches("[A-Z]+"))) {
            return false;
        }
        // Check digits in right positions
        if (code.substring(6, 8).matches("[0-9]+") && code.substring(9, 11).matches("[0-9]+") &&
                code.substring(12, 15).matches("[0-9]+")) {
            return false;
        }

        // Check date
        int birthDay = Integer.parseInt(code.substring(9, 11)) % 40;
        if (birthDay < 1 || birthDay > monthMap.getOrDefault(code.charAt(11), 31)) {
           return false;
        }
        return true;
    }


}
