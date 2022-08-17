package dev.mirplatform;

import java.util.Map;

public class RomanDigits {


    private static final Map<Integer, String> NUMBERS = Map.of(
            1 , "I",
            2, "II",
            3, "III",
            4, "IV",
            5, "V",
            6, "VI",
            7, "VII",
            8, "VIII",
            9, "IX",
            10, "X"
    );

    public static String convert(int arabic) {
        return NUMBERS.get(arabic);
    }

}
