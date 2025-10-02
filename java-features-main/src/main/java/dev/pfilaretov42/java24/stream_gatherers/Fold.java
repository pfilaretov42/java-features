package dev.pfilaretov42.java24.stream_gatherers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Gatherers;

public class Fold {
    void main() {
        before();
        IO.println("============");
        after();
    }

    private void before() {
        List<Integer> orcsDefeated = List.of(3, 2, 2, 5, 8, -2, 5, 6, 9);
        String numberString = orcsDefeated.stream()
            .map(String::valueOf)
            .reduce("", String::concat);
        IO.println("Эй, Леголас, а у меня фрагов " + numberString);


        // Эй, Леголас, а у меня фрагов 32258-2569
    }

    private void after() {
        List<Integer> orcsDefeated = List.of(3, 2, 2, 5, 8, -2, 5, 6, 9);
        Optional<String> numberString = orcsDefeated.stream()
            .gather(Gatherers.fold(()->"", (string, number) -> string + number))
            .findFirst();
        IO.println("Эй, Леголас, а у меня фрагов " + numberString);
    }
}
