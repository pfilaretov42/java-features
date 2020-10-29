package pro.filaretov.java9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * New Optional features
 */
public class OptionalFeatures {

    public static void main(String[] args) {
        ifPresentOrElse();
        or();
        stream();
    }

    private static void ifPresentOrElse() {
        Optional<String> result = getResult(-1L);
        result.ifPresentOrElse(s -> System.out.println("ifPresentOrElse(): result = " + s),
            () -> System.out.println("ifPresentOrElse(): not found"));
    }

    private static void or() {
        Optional<String> result = getNonEmptyResult(-2L);
        System.out.println("or(): " + result.orElse("The thing that should not be!"));
    }

    private static void stream() {
        Optional<String> result = getNonEmptyResult(42L);
        List<String> resultAsList = result.stream()
            .map(value -> value + " (mapped)")
            .collect(Collectors.toList());

        System.out.println("stream(): resultAsList = " + resultAsList);
    }

    private static Optional<String> getNonEmptyResult(Long id) {
        return getResult(id).or(() -> Optional.of("Yuk"));
    }

    private static Optional<String> getResult(Long id) {
        return id != null && id > 0 ? Optional.of("Yay!") : Optional.empty();
    }
}

