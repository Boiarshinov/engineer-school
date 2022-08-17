package dev.mirplatform;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanDigitsTest {

    @ParameterizedTest
    @CsvSource({
        "1,I",
        "2,II",
        "3,III",
        "4,IV",
        "5,V",
        "6,VI",
        "7,VII",
        "8,VIII",
        "9,IX",
        "10,X"
    })
    void fromOneToTen(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }

    @ParameterizedTest
    @CsvSource({
        "11,XI",
        "12,XII",
        "13,XIII",
        "14,XIV",
        "15,XV",
        "16,XVI",
        "17,XVII",
        "18,XVIII",
        "19,XIX",
        "20,XX"
    })
    void fromTenToTwenty(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }

    @ParameterizedTest
    @CsvSource({
            "50,L",
            "100,C",
            "500,D",
            "1000,M"
    })
    void roundNumbers(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }


}