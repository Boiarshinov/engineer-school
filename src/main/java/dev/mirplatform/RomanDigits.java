package dev.mirplatform;

import java.util.HashMap;
import java.util.Map;

public class RomanDigits {

    private static final Integer M = 1000;
    private static final Integer D = 500;
    private static final Integer C = 100;
    private static final Integer L = 50;
    private static final Integer X = 10;

    private static final Map<Integer, String> NUMBERS = new HashMap<>() {
        {
            put(1, "I");
            put(2, "II");
            put(3, "III");
            put(4, "IV");
            put(5, "V");
            put(6, "VI");
            put(7, "VII");
            put(8, "VIII");
            put(9, "IX");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");
        }
    };

    public static String convert(int arabic) {
        if (arabic > 3000) {
            throw new IllegalArgumentException("Недопустимое число: " + arabic);
        }
        if (arabic >= M) {
            return "M";
        } else if (arabic >= D) {
            return "D";
        } else if (arabic >= C) {
            return "C";
        } else if (arabic >= L) {
            return "L";
        } else {
            if (arabic <= 10) {
                return NUMBERS.get(arabic);
            }
            if (arabic <= 20) {
                return "X" + NUMBERS.get(arabic - 10);
            }
        }
        return null;
    }

}
