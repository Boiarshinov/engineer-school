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
}