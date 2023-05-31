package dev.pfilaretov42.java5.enumset;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet<Quote> allQuotes = EnumSet.allOf(Quote.class);
        EnumSet<Quote> noQuotes = EnumSet.noneOf(Quote.class);
        EnumSet<Quote> twoQuotes = EnumSet.of(Quote.DONT_PANIC, Quote.TOWEL);
        EnumSet<Quote> threeQuotes = EnumSet.range(Quote.DONT_PANIC, Quote.TOWEL);
        EnumSet<Quote> sameThreeQuotes = EnumSet.complementOf(EnumSet.of(Quote.SO_LONG));

        assertEquals(4, allQuotes.size());
        assertTrue(noQuotes.isEmpty());
        assertEquals(2, twoQuotes.size());
        assertEquals(3, threeQuotes.size());
        assertEquals(threeQuotes, sameThreeQuotes);
    }
}
