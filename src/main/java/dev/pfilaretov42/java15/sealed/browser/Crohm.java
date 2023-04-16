package dev.pfilaretov42.java15.sealed.browser;

/**
 * Sealed class {@link Browser} can be extended with non-sealed class {@link Crohm}.
 */
public non-sealed class Crohm extends Browser {

    @Override
    void browse() {
        System.out.println("Crohm in action");
    }
}

/**
 * Non-sealed class {@link Crohm} can be extended with any class.
 */
class HandlessCrohm extends Crohm {

    @Override
    void browse() {
        System.out.println("HandlessCrohm rocks!");
    }
}