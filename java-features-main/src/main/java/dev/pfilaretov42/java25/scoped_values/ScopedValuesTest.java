package dev.pfilaretov42.java25.scoped_values;

import java.lang.ScopedValue;

public class ScopedValuesTest {
    // Like the Phial of Galadriel - light that's safely contained
    private static final ScopedValue<String> CURRENT_RING_BEARER = ScopedValue.newInstance();

    void safeJourney() {
        ScopedValue.where(CURRENT_RING_BEARER, "Frodo")
                .run(this::travelToMordorSafely);
    }

    private void travelToMordorSafely() {
        // Only accessible in this scope, cannot be modified
        String bearer = CURRENT_RING_BEARER.get();
        System.out.println(bearer + " bears the burden safely");

        // Attempting to rebind would result in compilation error:
        //CURRENT_RING_BEARER.set("Sam");
    }

    void noJourney() {
        // Not accessible outside the scope, throws NoSuchElementException (ScopedValue not bound):
        String bearer = CURRENT_RING_BEARER.get();
        System.out.println(bearer + " bears the burden");
    }

    public static void main() {
        ScopedValuesTest test = new ScopedValuesTest();
        test.safeJourney();
        test.noJourney();
    }
}

class ThreadLocalTest {
    // Like carrying the One Ring - dangerous if shared carelessly
    private static final ThreadLocal<String> CURRENT_RING_BEARER = new ThreadLocal<>();

    void dangerousJourney() {
        CURRENT_RING_BEARER.set("Frodo");
        try {
            // The value could be changed by anyone in this thread
            travelToMordor();
        } finally {
            CURRENT_RING_BEARER.remove();
        }
    }

    void travelToMordor() {
        // Any method in the call chain can access...
        String bearer = CURRENT_RING_BEARER.get();
        System.out.println(bearer + " bears the burden");

        // ...and modify
        CURRENT_RING_BEARER.set("Sam");
        bearer = CURRENT_RING_BEARER.get();
        System.out.println(bearer + " bears the burden");
    }

    public static void main() {
        ThreadLocalTest test = new ThreadLocalTest();
        test.dangerousJourney();
    }
}