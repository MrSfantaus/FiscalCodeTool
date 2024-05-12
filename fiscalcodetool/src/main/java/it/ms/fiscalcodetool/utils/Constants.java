package it.ms.fiscalcodetool.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String OK  = "OK";    
    public static final String KO  = "KO";
    public static final String PATH_FILE = "files/COD_BELFIORE.json";

    protected static final String FISCALCODE_REGEX = "^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$";

    protected static final String FEMALE_CHAR = "F";

    protected static final String MALE_CHAR = "M";

    protected static final Map<String, String> HOMOCODICIAN_MAP = new HashMap<String, String>() {
        {
            put("L", "0");
            put("M", "1");
            put("N", "2");
            put("P", "3");
            put("Q", "4");
            put("R", "5");
            put("S", "6");
            put("T", "7");
            put("U", "8");
            put("V", "9");
        }
    };

    protected static final int[] HOMOCODICIAN_POS_ARRAY = { 6, 7, 9, 10, 12, 13, 14 };

    protected static final Map<String, Integer> ODDS_CHAR_MAP = new HashMap<String, Integer>() {
        {
            put("0", 0);
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("A", 0);
            put("B", 1);
            put("C", 2);
            put("D", 3);
            put("E", 4);
            put("F", 5);
            put("G", 6);
            put("H", 7);
            put("I", 8);
            put("J", 9);
            put("K", 10);
            put("L", 11);
            put("M", 12);
            put("N", 13);
            put("O", 14);
            put("P", 15);
            put("Q", 16);
            put("R", 17);
            put("S", 18);
            put("T", 19);
            put("U", 20);
            put("V", 21);
            put("W", 22);
            put("X", 23);
            put("Y", 24);
            put("Z", 25);
        }
    };

    protected static final Map<String, Integer> EVEN_CHAR_MAP = new HashMap<String, Integer>() {
        {
            put("0", 1);
            put("1", 0);
            put("2", 5);
            put("3", 7);
            put("4", 9);
            put("5", 13);
            put("6", 15);
            put("7", 17);
            put("8", 19);
            put("9", 21);
            put("A", 1);
            put("B", 0);
            put("C", 5);
            put("D", 7);
            put("E", 9);
            put("F", 13);
            put("G", 15);
            put("H", 17);
            put("I", 19);
            put("J", 21);
            put("K", 2);
            put("L", 4);
            put("M", 18);
            put("N", 20);
            put("O", 11);
            put("P", 3);
            put("Q", 6);
            put("R", 8);
            put("S", 12);
            put("T", 14);
            put("U", 16);
            put("V", 10);
            put("W", 22);
            put("X", 25);
            put("Y", 24);
            put("Z", 23);
        }
    };

    protected static final String[] CONTROL_CHAR_ARRAY = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    protected static final Map<String, String> MONTHS_CHAR_MAP = new HashMap<String, String>() {
        {
            put("A", "01");
            put("B", "02");
            put("C", "03");
            put("D", "04");
            put("E", "05");
            put("H", "06");
            put("L", "07");
            put("M", "08");
            put("P", "09");
            put("R", "10");
            put("S", "11");
            put("T", "12");
        }
    };

    protected static final String[] ERROR_LIST_MESSAGE = {
            "Codice da analizzare assente",
            "Lunghezza codice da analizzare non corretta",
            "Il codice da analizzare contiene caratteri non corretti",
            "Carattere non valido in decodifica omocodia",
            "Codice fiscale non corretto"
    };

    protected static final String VALID_MESSAGE = "Codice fiscale valido";

}
