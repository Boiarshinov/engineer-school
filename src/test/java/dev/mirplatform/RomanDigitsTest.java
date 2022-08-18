package dev.mirplatform;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            "50,L",
            "100,C",
            "500,D",
            "1000,M"
    })
    void roundNumbers(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }

    @Test
    public void noMore3000() {
        assertThrows(IllegalArgumentException.class, () -> RomanDigits.convert(3001));
    }

    @Test
    public void notLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> RomanDigits.convert(0));
    }

    @ParameterizedTest
    @CsvSource({
        "47,XLVII",
        "88,LXXXVIII",
        "99,XCIX",
        "246,CCXLVI",
        "789,DCCLXXXIX",
        "2421,MMCDXXI",
        "1066,MLXVI"
    })
    public void complexCases(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }
}
