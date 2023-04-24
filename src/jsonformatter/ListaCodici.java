package jsonformatter;

import main.TaxIdCode;

import java.util.ArrayList;

public class ListaCodici {

    ArrayList<String> invalidi = new ArrayList<>();
    ArrayList<String> spaiati = new ArrayList<>();

    public void addCode(TaxIdCode taxIdCode) {
        if (taxIdCode.isValid()) {
            spaiati.add(taxIdCode.getCode());
            return;
        }
        invalidi.add(taxIdCode.getCode());
    }

}
