package dev.pfilaretov42.java22.ffm_api;

public class ElvenScrollBefore {
    static {
        // Load the native library forged in C
        System.loadLibrary("mordor");
    }

    // Declare the native spell that reads the length of ancient text
    public native int countRunes(String ancientText);

    public static void main() {
        ElvenScrollBefore scroll = new ElvenScrollBefore();
        String runes = "Speak, friend, and enter";
        int length = scroll.countRunes(runes);
        System.out.println("Runes counted: " + length);
    }
}
