package dev.mirplatform;

public class RomanDigits {

    public static String convert(int arabic) {
        if (arabic > 3000 || arabic <= 0) {
            throw new IllegalArgumentException("Недопустимое число: " + arabic);
        }

        return convertArabicToRoman(arabic, new StringBuilder(), Roman.M).toString();
    }

    private static StringBuilder convertArabicToRoman(int arabic, StringBuilder buffer, Roman currentRomanDigit) {
        if (arabic == 0) {
            return buffer;
        }
        if (arabic - currentRomanDigit.value >= 0) {
            return convertArabicToRoman(
                    arabic - currentRomanDigit.value,
                    buffer.append(currentRomanDigit.name()),
                    currentRomanDigit
            );
        }

        int prefixSubtracted = arabic - (currentRomanDigit.value - currentRomanDigit.toSubtract.value);
        if (prefixSubtracted >= 0 && prefixSubtracted <= currentRomanDigit.toSubtract.value) {
            return convertArabicToRoman(
                    prefixSubtracted,
                    buffer.append(currentRomanDigit.toSubtract.name()).append(currentRomanDigit.name()),
                    currentRomanDigit.next
            );
        }
        return convertArabicToRoman(arabic, buffer, currentRomanDigit.next);
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
