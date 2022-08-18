package dev.mirplatform;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanDigits {

    public static String convert(int arabic) {
        if (arabic > 3000 || arabic <= 0) {
            throw new IllegalArgumentException("Недопустимое число: " + arabic);
        }

        int thousands = arabic / 1000;
        int hundreds = (arabic % 1000) / 100;
        int tens = (arabic % 100) / 10;
        int ones = arabic % 10;

        return Stream.of(
                oneDigitRule(thousands, Scale.THOUSANDS),
                oneDigitRule(hundreds, Scale.HUNDREDS),
                oneDigitRule(tens, Scale.TENS),
                oneDigitRule(ones, Scale.ONES)
            )
            .flatMap(Collection::stream)
            .map(Enum::name)
            .collect(Collectors.joining(""));
    }

    private enum Roman {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        public final int value;

        Roman(int value) {
            this.value = value;
        }
    }

    private enum Scale {
        ONES(Roman.I, Roman.V, Roman.X),
        TENS(Roman.X, Roman.L, Roman.C),
        HUNDREDS(Roman.C, Roman.D, Roman.M),
        THOUSANDS(Roman.M, null, null);

        public final Roman one;
        public final Roman five;
        public final Roman ten;

        Scale(Roman one, Roman five, Roman ten) {
            this.one = one;
            this.five = five;
            this.ten = ten;
        }
    }

    private static List<Roman> oneDigitRule(int a, Scale scale) {
        switch (a) {
            case 0: return List.of();
            case 1: return List.of(scale.one);
            case 2: return List.of(scale.one, scale.one);
            case 3: return List.of(scale.one, scale.one, scale.one);
            case 4: return List.of(scale.one, scale.five);
            case 5: return List.of(scale.five);
            case 6: return List.of(scale.five, scale.one);
            case 7: return List.of(scale.five, scale.one, scale.one);
            case 8: return List.of(scale.five, scale.one, scale.one, scale.one);
            case 9: return List.of(scale.one, scale.ten);
            default: throw new IllegalArgumentException("param should be from 0 to 9, but was " + a);
        }
    }
}
