package dev.pfilaretov42.java24.stream_gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class WindowSliding {
    void main() {
        before();
        after();
    }

    private void before() {
        List<String> dancers = List.of("Frodo", "Pippin", "Sam", "Bilbo", "Sméagol");
        int windowSize = 3;

        List<List<String>> triples = new ArrayList<>();
        for (int i = 0; i <= dancers.size() - windowSize; i++) {
            triples.add(
                new ArrayList<>(dancers.subList(i, i + windowSize))
            );
        }

        IO.println(triples);

        // [[Frodo, Pippin, Sam],
        //         [Pippin, Sam, Bilbo],
        //                 [Sam, Bilbo, Sméagol]]
    }

    private void after() {
        IO.println(
            Stream.of("Frodo", "Pippin", "Sam", "Bilbo", "Sméagol")
                .gather(Gatherers.windowSliding(3))
                .toList()
        );
    }
}
