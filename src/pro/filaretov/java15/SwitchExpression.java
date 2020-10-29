package pro.filaretov.java15;


import pro.filaretov.java15.sealed.browser.Browser;
import pro.filaretov.java15.sealed.browser.Crohm;

/**
 * Switch as an expression
 */
public class SwitchExpression {

    public static void main(String[] args) {

        switchEnum(SwitchState.ON);
        switchEnum(SwitchState.OFF);

        switchString(SwitchState.ON.toString());
        switchString(SwitchState.OFF.toString());
        switchString("not a switch state");
        switchString("Question");
    }

    private static void switchEnum(SwitchState state) {
        int result = switch (state) {
            case ON -> 1;
            // default branch is unnecessary, as we are switching enum values
            //default -> -1;
            case OFF -> 0;
        };

        System.out.println("switchEnum: " + state + " = " + result);
    }

    private static void switchString(String str) {
        int result = switch (str) {
            // default branch can be placed anywhere, but it should exist, otherwise compilation error occurs
            default -> -1;
            case "ON" -> 1;
            case "OFF" -> 0;
            case "Stg", "Smthg", "Something" -> 21;
            case "Question" -> {
                System.out.println("    switchString: Question detected!");
                yield 42;
            }
        };

        System.out.println("switchString: " + str + " = " + result);
    }


    private static void switchWithNoResult(int i) {
        switch (i) {
            case 1 -> System.out.println("One");
            case 42 -> System.out.println("The Answer!");
            // default branch can be placed anywhere and is not mandatory since we are not returning a value
            //default -> System.out.println("Default");
        }
    }



}

enum SwitchState {
    ON, OFF
}

