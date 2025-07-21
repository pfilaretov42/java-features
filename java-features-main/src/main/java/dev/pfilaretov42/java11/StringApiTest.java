package dev.pfilaretov42.java11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test Java 11 String API additions.
 */
class StringApiTest {

    @Test
    public void repeat() {
        assertEquals("go go go ", "go ".repeat(3));
    }

    @Test
    public void stripWhitespaces() {
        // string that contains simple whitespaces as well as the Unicode whitespace '\u2005'
        String str = "\n\t  hello   \u2005";

        assertEquals("hello", str.strip());

        // this is different from trim():
        assertEquals("hello   \u2005", str.trim());
    }

    @Test
    public void isBlank() {
        // string that contains simple whitespaces as well as the Unicode whitespace '\u2005'
        String str = "\n\t   \u2005";

        assertTrue(str.isBlank());

        // this is different from both isEmpty() and trim().isEmpty():
        assertFalse(str.isEmpty());
        assertFalse(str.trim().isEmpty());
    }

    @Test
    void linesOfTextBlock() {
        String textBlock = """
            This is a 
            multiline text block.
            How do you
            rate the format-
            ting, uh?
            
            """;

        assertEquals(6, textBlock.lines().count());
        assertEquals(5, textBlock.lines().filter(s -> !s.isBlank()).count());
        assertTrue(textBlock.lines()
            .noneMatch(s -> s.contains("\r") || s.contains("\n") || s.contains("\r\n")));
    }

    @Test
    void linesOfPlainString() {
        String str = "This is a\r multiline text block.\r\nHow do you\nrate the format-\nting, uh?\r\n\r";

        assertEquals(6, str.lines().count());
    }
}