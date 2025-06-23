package dev.pfilaretov42.java25.flexible_constructor_bodies;

public class FlexibleConstructorBodiesTest {
}

class PalantirBefore {
    private final String owner;
    private final boolean isCorrupted;

    // Like the rigid walls of Orthanc - no flexibility
    public PalantirBefore(String owner) {
        // This would not compile:
        //validateOwner(owner);
        // Call to this() must be the first statement:
        this(validateOwner(owner), false);
    }

    private static String validateOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner cannot be null or blank");
        }
        System.out.println("A new palantir is given to " + owner);
        return owner;
    }

    private PalantirBefore(String owner, boolean isCorrupted) {
        this.owner = owner;
        this.isCorrupted = isCorrupted;
    }
}

class Palantir {
    private final String owner;
    private final boolean isCorrupted;

    // Now flows like the waters of Bruinen - natural and flexible
    public Palantir(String owner) {
        validateOwner(owner);
        // Now this() call is allowed after other statements:
        this(owner, false);
    }

    private static void validateOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner cannot be null or blank");
        }
        System.out.println("A new palantir is given to " + owner);
    }

    private Palantir(String owner, boolean isCorrupted) {
        this.owner = owner;
        this.isCorrupted = isCorrupted;
    }
}
