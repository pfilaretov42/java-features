package dev.pfilaretov42.java25.flexible_constructor_bodies;

public class FlexibleConstructorBodiesTest {
}

class PalantirBefore {
    private final String owner;
    private final boolean isCorrupted;

    // Like the rigid walls of Orthanc - no flexibility
    public PalantirBefore(String owner) {
        // This would not compile:
        //System.out.println("A new palantir is given to " + owner);
        // Must be the first statement:
        this(owner, false);
    }

    public PalantirBefore(String owner, boolean isCorrupted) {
        this.owner = owner;
        this.isCorrupted = isCorrupted;
    }
}

class Palantir {
    private final String owner;

    private final boolean isCorrupted;
    // Now flows like the waters of Bruinen - natural and flexible

    public Palantir(String owner) {
        System.out.println("A new palantir is given to " + owner);
        // Now this() call is allowed after other statements:
        this(owner, false);
    }
    public Palantir(String owner, boolean isCorrupted) {
        this.owner = owner;
        this.isCorrupted = isCorrupted;
    }

}
