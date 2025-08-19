package dev.pfilaretov42.java25.module_import_declarations;

import lothlorien.Celeborn;
import lothlorien.Galadriel;
import lothlorien.Haldir;

public class FarewellToLórienBefore {
    void main() {
        Haldir guardian = new Haldir();
        guardian.escort();

        Celeborn lord = new Celeborn();
        lord.offerCounsel();

        Galadriel lady = new Galadriel();
        lady.speakLightOfEärendil();
    }
}
