package dev.pfilaretov42.java24.stream_gatherers;


import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class StreamGatherersTest {
    public static void main() {
        before();
        after();
    }

    private static void before() {
        // Like trying to forge the One Ring with only basic tools
        List<String> hobbits = List.of("Frodo", "Sam", "Merry", "Pippin");

        // To get overlapping pairs, we needed a workarounds
        List<String> pairs = IntStream.range(0, hobbits.size() - 1)
                .mapToObj(i -> hobbits.get(i) + " & " + hobbits.get(i + 1))
                .toList();
        System.out.println(pairs);
        // Output: ["Frodo & Sam", "Sam & Merry", "Merry & Pippin"]
    }

    private static void after() {
        // Now wielding the power of the Elven smiths
        List<String> hobbits = List.of("Frodo", "Sam", "Merry", "Pippin");

        Gatherer<String, ?, String> pairing = Gatherer.ofSequential(
                () -> new Object() { String previous; },
                (state, element, downstream) -> {
                    if (state.previous != null) {
                        downstream.push(state.previous + " & " + element);
                    }
                    state.previous = element;
                    return true;
                }
        );

        List<String> pairs = hobbits.stream()
                .gather(pairing)
                .toList();
        System.out.println(pairs);
        // Output: ["Frodo & Sam", "Sam & Merry", "Merry & Pippin"]
    }
}
