package dev.pfilaretov42.java22.unnamed_vars_patterns;

public class UnnamedVariablesAndPatternsTest {
    // TODO - run, check, and copy to the post
    public static void main() {
        var balrog = new Balrog("Durin's Bane", 1000);
        before(balrog);
        after(balrog);
    }

    private static void before(Object creature) {
        // Like being forced to name every orc in Mordor's army
        try {
            int rings = forgeNewRing();
        } catch (RingForgingException e) {  // Never used
            System.out.println("The fires of Mount Doom failed us!");
        }

        // Pattern matching with unused bindings
        if (creature instanceof Balrog(String name, int power)) {
            System.out.println("Fly, you fools!");  // 'name' and 'power' unused
        }
    }

    private static void after(Object creature) {
        // As elegant as Legolas defying gravity
        try {
            int _ = forgeNewRing();
        } catch (RingForgingException _) {  // Clear this is unused
            System.out.println("The fires of Mount Doom failed us!");
        }

        // Clean pattern matching
        if (creature instanceof Balrog(_, _)) {
            System.out.println("Fly, you fools!");  // No false promises of usage
        }
    }

    private static int forgeNewRing() {
        return 0;
    }

    public void inspectFighter(Object fighter) {
        if (fighter instanceof Elf(_, Weapon(String type, _))) {  // name and damage unused
            System.out.println("Armed with: " + type);
        }
    }
}

record Weapon(String type, int damage) {}

record Elf(String name, Weapon weapon) {}

record Balrog(String name, int power) {
}

class RingForgingException extends RuntimeException {
}