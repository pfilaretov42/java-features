package pro.filaretov.java11;

import java.util.function.IntFunction;

/**
 * Local-variable syntax for Lambda parameters
 */
public class LocalVarForLambda {

    public static void main(String[] args) {
        int value = 50;

        // Java 8 syntax
        IntFunction<String> rubleFunction = x -> x + "₽";
        System.out.println(value + ": " + rubleFunction.apply(value));

        // we can use type here:
        IntFunction<String> euroFunction = (final int x) -> x + "€";
        System.out.println(value + ": " + euroFunction.apply(value));

        IntFunction<String> dollarFunction = (int x) -> (x = 2) + "$";
        System.out.println(value + ": " + dollarFunction.apply(value));

        // From Java 11 we can use var instead of int:
        //IntFunction<String> poundFunction = (final var x) -> (x = 2) + "£"; // compilation error
        IntFunction<String> poundFunction = (final var x) -> x + "£";
        System.out.println(value + ": " + poundFunction.apply(value));
    }
}
