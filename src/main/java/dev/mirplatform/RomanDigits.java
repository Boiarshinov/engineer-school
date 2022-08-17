package dev.mirplatform;

import java.util.HashMap;
import java.util.Map;

public class RomanDigits {

    private enum Roman {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10);

        final int arabic;

        Roman(int arabic) {
            this.arabic = arabic;
        }
    }

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
        }
    };

    public static String convert(int arabic) {
        if (arabic > 3000) {
            throw new IllegalArgumentException("Недопустимое число: " + arabic);
        }
        var builder = new StringBuilder();
        buildResult(builder, arabic);
        return builder.toString();
    }

    private static void buildResult(StringBuilder builder, int arabic) {
        if (arabic == 0) {
            return;
        }
        if (arabic < 10) {
            builder.append(NUMBERS.get(arabic));
            return;
        }
        for (var r : Roman.values()) {
            if (arabic >= r.arabic) {
                builder.append(r.name());
                arabic -= r.arabic;
                buildResult(builder, arabic);
                break;
            }
        }
    }

}
