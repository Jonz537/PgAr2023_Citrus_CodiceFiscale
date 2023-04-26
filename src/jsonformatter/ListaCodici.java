package jsonformatter;

import main.TaxIdCode;

import java.util.ArrayList;

public class ListaCodici {

    ArrayList<String> invalidi = new ArrayList<>();
    ArrayList<String> spaiati = new ArrayList<>();

    /**
     * save codes in invalidi and spaiati lists
     * @param taxIdCode read on CodiciFiscali.json
     */
    public void addCode(TaxIdCode taxIdCode) {
        if (taxIdCode.isValid()) {
            spaiati.add(taxIdCode.getCode());
            return;
        }
        invalidi.add(taxIdCode.getCode());
    }

}
