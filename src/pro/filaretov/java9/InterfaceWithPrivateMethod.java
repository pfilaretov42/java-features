package pro.filaretov.java9;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface can have private methods
 */
public interface InterfaceWithPrivateMethod {

    default long countMajorFeatures(List<String> features) {
        return countFeatures(features, f -> f.startsWith("MAJOR:"));
    }

    default long countMinorFeatures(List<String> features) {
        return countFeatures(features, f -> f.startsWith("MINOR:"));
    }

    private long countFeatures(List<String> features, Predicate<String> filterPredicate) {
        return features.stream().filter(filterPredicate).count();
    }
}
