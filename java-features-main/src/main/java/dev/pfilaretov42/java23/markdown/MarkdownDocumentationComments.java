package dev.pfilaretov42.java23.markdown;

public class MarkdownDocumentationComments {
}


/**
 * <h1>The One Ring</h1>
 * <p>Forged by Sauron in the fires of Mount Doom.</p>
 * <pre>{@code
 * if (ring.isFound()) {
 *     frodo.destroy(ring);
 * }
 * }</pre>
 * <p><b>Warning:</b> Do not wear for extended periods.</p>
 */
class OneRingHtml {
    // ...
}

/// # The One Ring
/// Forged by Sauron in the fires of Mount Doom.
/// ```java
/// if (ring.isFound()) {
///     frodo.destroy(ring);
/// }
/// ```
/// **Warning:** Do not wear for extended periods.
class OneRingMarkdown {
    // ...
}