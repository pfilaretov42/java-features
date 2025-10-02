package dev.pfilaretov42.java24.stream_gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class WindowFixed {
    void main() {
        before();
        after();
    }

    private void before() {
        List<String> dancers = List.of("Frodo", "Pippin", "Sam", "Bilbo", "Sméagol");
        int windowSize = 2;

        List<List<String>> pairs = new ArrayList<>();
        for (int i = 0; i <= dancers.size(); i += windowSize) {
            pairs.add(
                new ArrayList<>(dancers.subList(i, Math.min(i + windowSize, dancers.size())))
            );
        }

        IO.println(pairs);

        // [[Frodo, Pippin],
        //                   [Sam, Bilbo],
        //                                 [Sméagol]]
    }

    private void after() {
        IO.println(
            Stream.of("Frodo", "Pippin", "Sam", "Bilbo", "Sméagol")
                .gather(Gatherers.windowFixed(2))
                .toList()
        );
    }
}
