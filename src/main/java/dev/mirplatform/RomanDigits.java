package dev.mirplatform;

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
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        final int arabic;

        Roman(int arabic) {
            this.arabic = arabic;
        }
    }

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