package pro.filaretov.java15;

import java.time.LocalDate;
import java.util.Map;

/**
 * Records
 */
public class Records {

    public static void main(String[] args) {
        PersonRecord personRecord = new PersonRecord(1L, "Jim", LocalDate.of(2010, 1, 1));
        System.out.println(personRecord.id() + " (" + personRecord.name() + "): " + personRecord);

        AnotherRecord anotherRecord = new AnotherRecord(2L, "Something");
        System.out.println("anotherRecord: " + anotherRecord);

        YetAnotherRecord yetAnotherRecord = new YetAnotherRecord(3L, 42);
        System.out.println("yetAnotherRecord: " + yetAnotherRecord);

        OneMoreRecord oneMoreRecord = new OneMoreRecord(4L, "value1");
        System.out.println("oneMoreRecord: " + oneMoreRecord);
    }
}

record PersonRecord(Long id, String name, LocalDate dateOfBirth) {

    // implicit constructor

    @Override
    public String toString() {
        return "PersonRecord{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            '}';
    }
}

record AnotherRecord(Long id, String value) {

    /**
     * Compact constructor
     */
    AnotherRecord {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        // id and value initialised implicitly
    }

    @Override
    public String toString() {
        return "AnotherRecord{" +
            "id=" + id +
            ", value='" + value + '\'' +
            '}';
    }
}

record YetAnotherRecord(Long id, int sum) {

    /**
     * Canonical constructor
     */
    YetAnotherRecord(Long id, int sum) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        // id and sum need to be initialised explicitly
        this.id = id;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "YetAnotherRecord{" +
            "id=" + id +
            ", sum=" + sum +
            '}';
    }
}

record OneMoreRecord(Long id, Map<String, String> values) {

    /**
     * Custom constructor
     */
    public OneMoreRecord(Long id, String value) {
        // must delegate to one of the record constructors
        this(id, Map.of(id.toString(), value));
    }

    @Override
    public String toString() {
        return "OneMoreRecord{" +
            "id=" + id +
            ", values=" + values +
            '}';
    }
}