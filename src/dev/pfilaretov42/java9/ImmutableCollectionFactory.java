package dev.pfilaretov42.java9;

import java.util.List;
import java.util.Map;

/**
 * Immutable Collection Factory methods
 */
public class ImmutableCollectionFactory {

    public static void main(String[] args) {
        // LIST
        List<String> strings = List.of("Howdy", "Booooring!", "Good news, everyone!");
        System.out.println("strings = " + strings + " (%s)".formatted(strings.getClass().getName()));

        // more than 10 elements is var args method
        strings = List.of("Bach", "Handel", "Mozart", "Beethoven", "Liszt", "Paganini", "Chopin", "Schubert", "Haydn",
            "Sviridov", "... and more");
        System.out.println("strings = " + strings + " (%s)".formatted(strings.getClass().getName()));

        // throws UnsupportedOperationException
        //strings.add("Ferrari");

        // MAP
        Map<Integer, String> composers = Map.of(1, "Bach", 2, "Handel");
        System.out.println("composers = " + composers + " (%s)".formatted(composers.getClass().getName()));


    }
}
