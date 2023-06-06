package dev.pfilaretov42.java12;

import java.util.Scanner;

/**
 * See this Java 12 feature here:
 * - https://openjdk.org/jeps/346
 * - https://www.happycoders.eu/java/java-12-features/#Promptly_Return_Unused_Committed_Memory_from_G1
 * <p>
 * Enable G1 GC: -XX:+UseG1GC
 * Enable feature: -XX:G1PeriodicGCInterval
 * - The interval in ms to check whether G1 should trigger a periodic garbage collection.
 * - Set to zero to disable.
 */
public class DeepThought {
    public static void main(String[] args) {
        System.out.println("Press Enter to start...");
        new Scanner(System.in).nextLine();

        calculateTheAnswer();

        System.out.println("Press Enter to exit...");
        new Scanner(System.in).nextLine();
    }

    private static void calculateTheAnswer() {
        System.out.println("Calculating The Answer...");
        for (int i = 0; i < 500; i++) {
            int[] array = new int[1_000_000];
        }
        System.out.println("The Answer is... 42.");
    }
}
