package pro.filaretov.java10;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class UnmodifiableCollectionsImprovementsTest {

    @Test
    public void canCopy() {
        List<String> instruments = Arrays.asList("piano", "guitar", "drums");
        List<String> fakeInstruments = List.copyOf(instruments);
        assertNotNull(fakeInstruments);
        assertEquals(instruments.size(), fakeInstruments.size());
        assertTrue(fakeInstruments.containsAll(instruments));
    }

    @Test
    public void cannotModifyCopiedCollection() {
        Set<String> instruments = new HashSet<>(Arrays.asList("piano", "guitar", "drums"));
        List<String> fakeInstruments = List.copyOf(instruments);
        assertNotNull(fakeInstruments);
        assertEquals(instruments.size(), fakeInstruments.size());
        assertTrue(fakeInstruments.containsAll(instruments));

        instruments.add("organ");
        assertEquals(4, instruments.size());
        assertEquals(3, fakeInstruments.size());

        assertThrows(UnsupportedOperationException.class, () -> fakeInstruments.add("organ"));
    }

    @Test
    void collectToUnmodifiable() {
        List<String> modifiableList = Stream.generate(() -> UUID.randomUUID().toString())
            .limit(3)
            .collect(Collectors.toList());
        assertDoesNotThrow(() -> modifiableList.add("123"));

        List<String> unmodifiableList = modifiableList.stream().collect(Collectors.toUnmodifiableList());
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.add("abc"));
    }
}
