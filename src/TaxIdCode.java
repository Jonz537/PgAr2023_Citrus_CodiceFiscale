import java.util.HashMap;
import java.util.Map;

public class TaxIdCode {
    private String code;
    private final static HashMap<Character, Integer> monthMap = new HashMap<>(Map.of('b', 28,
            'd', 30, 'h', 30,'p', 30, 's', 30));
    

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
