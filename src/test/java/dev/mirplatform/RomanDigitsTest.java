package dev.mirplatform;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RomanDigitsTest {

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

    @ParameterizedTest
    @CsvFileSource(resources = "/1-100.txt", delimiter = '\t')
    void from1to100(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }

    @Test
    public void noMore3000() {
        assertThrows(IllegalArgumentException.class, () -> RomanDigits.convert(3001));
    }

    @ParameterizedTest
    @CsvSource({
            "1984,MCMLXXXIV",
            "2984,MMCMLXXXIV",
            "1774,MDCCLXXIV",
            "900,CM",
            "200,CC"
    })
    public void bigNumbers(int arabic, String expectedRoman) {
        String actualRoman = RomanDigits.convert(arabic);
        assertEquals(expectedRoman, actualRoman);
    }




}