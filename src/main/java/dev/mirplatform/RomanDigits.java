package dev.mirplatform;

public class RomanDigits {

    public static String convert(int arabic) {
        if (arabic > 3000 || arabic <= 0) {
            throw new IllegalArgumentException("Недопустимое число: " + arabic);
        }

        return convertArabicToRoman(arabic, "", Roman.M);
    }

    private static String convertArabicToRoman(int arabic, String buffer, Roman current) {
        if (arabic == 0) {
            return buffer;
        }
        if (arabic - current.value >= 0) {
            return buffer + current.name() + convertArabicToRoman(arabic - current.value, buffer, current);
        }

        int prefixToSubtract = arabic - (current.value - current.toSubtract.value);
        if (prefixToSubtract >= 0 && prefixToSubtract <= current.toSubtract.value) {
            return buffer + current.toSubtract.name() + current.name() + convertArabicToRoman(arabic - current.value + current.toSubtract.value, buffer, current.next);
        }
        return buffer + convertArabicToRoman(arabic, buffer, current.next);
    }

    private enum Roman {
        I(1, null, null), V(5, I, I), X(10, V, I), L(50, X, X), C(100, L, X), D(500, C, C), M(1000, D, C);

        Roman(int value, Roman next, Roman toSubtract) {
            this.value = value;
            this.next = next;
            this.toSubtract = toSubtract;
        }

        final int value;
        final Roman next;
        final Roman toSubtract;
    }

}
