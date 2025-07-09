package dev.pfilaretov42.java22.ffm_api;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class ForeignFunctionAndMemoryApiTest {
    void main() throws Throwable {
        before();
        after();
        afterSven();
    }

    void before() {
        // Usage:
        try {
            UnsafeRingWrapper wrapper = new UnsafeRingWrapper();
            wrapper.corruptMemory(0xDEADBEEF); // Good luck debugging this!
        } catch (UnsatisfiedLinkError e) {
            // The Nazgûl of programming errors
        }
    }

    void afterSven() throws Throwable {
        // 1. Get a lookup object for commonly used libraries
        SymbolLookup stdlib = Linker.nativeLinker().defaultLookup();

        // 2. Get a handle to the "strlen" function in the C standard library
        MethodHandle strlen = Linker.nativeLinker().downcallHandle(
            stdlib.find("strlen").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
        );

        // 3. Get a confined memory area (one that we can close explicitly)
        try (Arena offHeap = Arena.ofConfined()) {

            // 4. Convert the Java String to a C string and store it in off-heap memory
            MemorySegment str = offHeap.allocateFrom("Mellon");

            // 5. Invoke the foreign function
            long length = (long) strlen.invoke(str);
            System.out.println("length = " + length);
        }
        // 6. Off-heap memory is deallocated at end of try-with-resources
    }

    void after() throws Throwable {
        // Now, like the Elven bridges of Lothlórien - elegant and secure
        // Safely interact with C libraries
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();

        MethodHandle strlen = linker.downcallHandle(
            stdlib.find("strlen").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
        );

        try (Arena arena = Arena.ofConfined()) {
            // Allocate off-heap memory as easily as forging a new sword
            MemorySegment str = arena.allocateFrom("Mellon");

            // Call C's strlen() safely
            long length = (long) strlen.invoke(str);
            System.out.println("Elvish word length: " + length);
        } // Memory automatically freed here - no memory leaks!
    }
}

// Like the treacherous paths of Morgul Vale, full of hidden dangers
class UnsafeRingWrapper {
    static {
        System.loadLibrary("sauronCore"); // Load the Dark Lord's native library
    }

    // A fragile bridge that could collapse at any moment
    public native void corruptMemory(long ptr);

    public native String summonBalrog(); // May summon a Segfault instead
}