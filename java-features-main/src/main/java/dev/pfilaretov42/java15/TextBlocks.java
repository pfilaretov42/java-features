package dev.pfilaretov42.java15;

/**
 * Text blocks
 */
public class TextBlocks {
    private static final String textBlock = """
        {
            "id": "1",
            "name": "Peter"
        }
        """;


    public static void main(String[] args) {
        System.out.println(textBlock);

        formattedString();

        checkNewLineChar();
    }

    private static void formattedString() {
        String sql = """
        SELECT * FROM table1 WHERE %s = ?;
        """;
        String formattedSql = sql.formatted("id");
        System.out.println(formattedSql);
    }

    private static void checkNewLineChar() {
        String strWithNewLine = """
            Block
            with
            new line
            at the end.
            """;
        String strWithoutNewLine = """
            Block
            without
            new line
            at the end.""";
        System.out.println("strWithNewLine ends with new line char: " + strWithNewLine.endsWith("\n"));
        System.out.println("strWithoutNewLine ends with new line char: " + strWithoutNewLine.endsWith("\n"));
    }
}
