package dev.pfilaretov42.java5.enumset;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Quote {
    DONT_PANIC("Don't Panic."),
    THE_ANSWER("42"),
    TOWEL("A towel is about the most massively useful thing an interstellar hitchhiker can have."),
    SO_LONG("So long, and thanks for all the fish."),
    ;

    private final String value;
}