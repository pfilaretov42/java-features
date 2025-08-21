package dev.pfilaretov42.java22.ffm_api;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class ElvenScrollAfter {

    public static void main() throws Throwable {
        // Now, like the Elven bridges of Lothlórien - elegant and secure

        // Get a linker and a lookup object
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();

        // Get a handle to the foreign function ('strlen' from the C standard library)
        MethodHandle strlen = linker.downcallHandle(
            stdlib.find("strlen").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
        );

        try (Arena arena = Arena.ofConfined()) {
            // Allocate off-heap memory
            String runes = "Speak, friend, and enter";
            MemorySegment cString = arena.allocateFrom(runes);

            // Call the foreign function
            long runeCount = (long) strlen.invoke(cString);
            System.out.println("Runes counted: " + runeCount);
        }
        // Memory automatically freed here - no memory leaks!
        // No native code!
    }
}
