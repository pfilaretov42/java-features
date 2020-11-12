package pro.filaretov.java10;

import java.util.function.Supplier;

/**
 *
 */
public class LocalVariableTypeInference {

    // Cannot use var as a class field declaration
    //private var aString = "Str";

    public static void main(String[] args) {
        methodWithParameter("Oops!");
        cannotInferType();
    }

    /**
     * Cannot use {@code var} as a parameter declaration, i.e., {@code var s}
     *
     * @param s string
     */
    private static void methodWithParameter(String s) {
        var chars = s.toCharArray();
        for (var c : chars) {
            System.out.println("char: " + c);
        }
    }

    private static void cannotInferType() {
        //var myVar;
        //var ints = {1, 2, 3};
        //var supplier = () -> "value";
        var supplier = (Supplier<String>) () -> "value";
    }
}
