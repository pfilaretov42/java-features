package dev.pfilaretov42.test;

sealed class Hobbit {}
final class Frodo extends Hobbit {}

public class TestClassProperties {
    void main() {
        var hobbitClass = Hobbit.class;
        System.out.println(STR."Hobbit class: isSealed=\{hobbitClass.isSealed()}");

        var frodoClass = Frodo.class;
        System.out.println(STR."Frodo class: isSealed=\{frodoClass.isSealed()}");
    }
}
