package pro.filaretov.java15.sealed;

/**
 * Sealed class which has no explicit {@code permits} clause, permits inner classes implicitly.
 */
public sealed abstract class Switcher {

    abstract void doSwitch();
}

final class SimpleSwitcher extends Switcher {

    @Override
    public void doSwitch() {
        System.out.println("switched on");
    }
}

final class DoubleSwitcher extends Switcher {

    @Override
    public void doSwitch() {
        System.out.println("1 switched on, 2 switched off");
    }
}



