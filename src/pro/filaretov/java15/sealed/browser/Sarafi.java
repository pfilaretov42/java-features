package pro.filaretov.java15.sealed.browser;

/**
 * Sealed class {@link Browser} can be extended with sealed class {@link Sarafi}.
 */
public sealed class Sarafi extends Browser permits Sarafi13, Sarafi14 {

    @Override
    void browse() {
        System.out.println("Sarafi's here");
    }

}

final class Sarafi13 extends Sarafi {

}

non-sealed class Sarafi14 extends Sarafi {

}