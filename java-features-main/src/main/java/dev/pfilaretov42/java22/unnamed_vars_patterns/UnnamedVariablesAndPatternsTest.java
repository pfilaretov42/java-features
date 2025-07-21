package dev.pfilaretov42.java22.unnamed_vars_patterns;

public class UnnamedVariablesAndPatternsTest {
    // TODO - run, check, and copy to the post
    public static void main() {
        var balrog = new Balrog("Durin's Bane", 1000);
        before(balrog);
        after(balrog);
    }

    private static void before(Object fighter) {
// Like being forced to name every orc in Mordor's army
try {
    int rings = forgeNewRing();
} catch (RingForgingException e) {  // Never used
    System.out.println("The fires of Mount Doom failed us!");
}

// Pattern matching with unused bindings
if (fighter instanceof Elf(String name, Weapon(String type, int damage))) {  // name and damage unused
    System.out.println("Armed with: " + type);
}
    }

    private static void after(Object fighter) {
// As elegant as Legolas defying gravity
try {
    int _ = forgeNewRing();
} catch (RingForgingException _) {  // Clear this is unused
    System.out.println("The fires of Mount Doom failed us!");
}

// Clean pattern matching
if (fighter instanceof Elf(_, Weapon(String type, _))) {
    System.out.println("Armed with: " + type);
}
    }

    private static int forgeNewRing() {
        return 0;
    }

}

record Weapon(String type, int damage) {}

record Elf(String name, Weapon weapon) {}

record Balrog(String name, int power) {
}

class RingForgingException extends RuntimeException {
}