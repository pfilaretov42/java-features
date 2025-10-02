package dev.pfilaretov42.java24.stream_gatherers;

import java.util.List;
import java.util.stream.Gatherers;

public class Scan {
    void main() {
        before();
        IO.println("============");
        after();
    }

    private void before() {
        List<Integer> orcsDefeated = List.of(3, 2, 2, 5, 8, -2, 5, 6, 9);
        int slidingSum = 0;

        for (int frags : orcsDefeated) {
            IO.println("Эй, Леголас, а у меня уже " + (slidingSum += frags));
        }


        // Эй, Леголас, а у меня уже 3
        // Эй, Леголас, а у меня уже 5
        // Эй, Леголас, а у меня уже 7
        // ...
    }

    private void after() {
        List<Integer> orcsDefeated = List.of(3, 2, 2, 5, 8, -2, 5, 6, 9);
        orcsDefeated.stream()
            .gather(Gatherers.scan(() -> 0, Integer::sum))
            .forEach(i -> IO.println("Эй, Леголас, а у меня уже " + i));
    }
}
