package dev.pfilaretov42.java21.sequensedcollections;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SequencedCollectionsHome {
    public static void main(String[] args) {
        sequencedList();

        sequencedSet();
    }

    private static void sequencedSet() {
        LinkedHashSet<String> hobbits = new LinkedHashSet<>(List.of("Frodo", "Sam", "Pippin", "Merry"));
        String firstHobbit = hobbits.getFirst();
        String lastHobbit = hobbits.getLast();
        System.out.println("First hobbit: " + firstHobbit);

    }

    private static void sequencedList() {
        List<String> hobbits = List.of("Frodo", "Sam", "Pippin", "Merry");
//        String lastHobbit = hobbits.get(hobbits.size() - 1);
        String lastHobbit = hobbits.getLast();
        System.out.println("Last hobbit: " + lastHobbit);
    }
}
