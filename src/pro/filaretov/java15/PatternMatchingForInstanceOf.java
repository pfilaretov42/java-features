package pro.filaretov.java15;

/**
 * Pattern matching for {@code instanceof}
 */
public class PatternMatchingForInstanceOf {

    public static void main(String[] args) {
        Object object = "I'm a srtnig";
        validate(object);
        visibilityCheck1(object);

        object = 5;
        validate(object);
        visibilityCheck1(object);
    }

    private static void validate(Object object) {
        if (object instanceof String s) {
            System.out.println("String found: " + s.trim());
        } else if (object instanceof Integer i) {
            // s is not visible here - compilation error:
            //System.out.println("String found: " + s.trim());

            System.out.println("Integer found: " + i);
        } else {
            // s is not visible here - compilation error:
            //System.out.println("String found: " + s.trim());

            System.out.println("Unknown type found: " + object.getClass().getSimpleName());
        }

        // s is not visible here - compilation error:
        //System.out.println("String found: " + s.trim());
    }

    private static void visibilityCheck1(Object object) {
        if (!(object instanceof String s)) {
            // s is not visible here - compilation error:
            //throw new IllegalArgumentException("not a string: " + s);

            throw new IllegalArgumentException("not a string"); // or 'return;'
        } else {
            // s is visible in true block
            System.out.println("String found: " + s.trim());
        }

        // s is visible outside of 'if' block, if method exits inside 'if'
        System.out.println("String found: " + s.trim());
    }

    private static void visibilityCheck2(Object object) {
        if (object instanceof String s) {
            System.out.println("String found: " + s.trim());
        } else {
            // s is not visible here - compilation error:
            //throw new IllegalArgumentException("not a string: " + s);

            throw new IllegalArgumentException("not a string"); // or 'return;'
        }

        // s is visible outside of 'if' block, if method exits inside 'else'
        System.out.println("String found: " + s.trim());
    }

    private static void visibilityCheck3(Object object) {
        // s is in scope on the right hand side of the && operator
        if (object instanceof String s && !s.isBlank()) {
            System.out.println("String found: " + s.trim());
        }
    }

    private static String str = "Oops!";

    private static void visibilityCheck4(Object object) {
        // str is in scope of if block, but not in else
        if (object instanceof String str) {
            System.out.println("String found: " + str.trim());
        } else {
            System.out.println("String field: " + str.trim());
        }
    }

    private static void visibilityCheck5(Object object) {
        // str is not in scope on the right hand side of the || operator, neither do in if block
        if (object instanceof String str || !str.isBlank()) {
            System.out.println("String field: " + str.trim());
        } else {
            System.out.println("String field: " + str.trim());
        }
    }

    private static void assignmentCheck(Object object) {
        if (object instanceof String s) {
            System.out.println("String found: " + s.trim());

            // s is implicitly final, cannot assign a new value - compilation error:
            //s += " (Modified)";
        } else {
            return;
        }

        System.out.println("Modified string: " + s.trim());
    }

}
