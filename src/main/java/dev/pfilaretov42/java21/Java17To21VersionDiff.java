package dev.pfilaretov42.java21;

import java.util.List;
import java.util.stream.Stream;

public class Java17To21VersionDiff {
    public static void main(String[] args) {
        List<JavaVersion> javaVersions = List.of(
                new JavaVersion(17, List.of("17.1", "17.2")),
                new JavaVersion(18, List.of("18.1", "18.2")),
                new JavaVersion(19, List.of("19.1", "19.2")),
                new JavaVersion(20, List.of("20.1", "20.2")),
                new JavaVersion(21, List.of("21.1", "21.2")),
                new JavaVersion(22, List.of("22.1", "22.2"))
        );

        List<String> jeps = null;
javaVersions.stream()
        .filter(v -> v.version() > 17 && v.version() <= 21)
        .flatMap(v -> v.jeps().stream())
        .toList();

        System.out.println(jeps);

    }
}

record JavaVersion(Integer version, List<String> jeps) {
}

record JavaFeature(List<String> jeps) {
}