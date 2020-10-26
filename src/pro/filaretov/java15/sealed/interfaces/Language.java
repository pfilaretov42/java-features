package pro.filaretov.java15.sealed.interfaces;

/**
 * Interface can be sealed.
 */
public sealed interface Language permits NonJvmLanguage, Jvaa, Slaca {

    void compile();
}

/**
 * Sealed interface {@link Language} can be extended with another interface.
 */
non-sealed interface NonJvmLanguage extends Language {

}

/**
 * Simple final class which implements sealed interface {@link Language}.
 */
final class Jvaa implements Language {

    @Override
    public void compile() {
        System.out.println("Compilation completed successfully.");
    }
}

/**
 * Record can also implement sealed interface, and it's implicitly final
 */
record Slaca(String id) implements Language {

    @Override
    public void compile() {
        System.out.println("Compilation is done.");
    }
}