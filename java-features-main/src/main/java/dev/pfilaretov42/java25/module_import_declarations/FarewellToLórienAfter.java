package dev.pfilaretov42.java25.module_import_declarations;

// TODO - gradle fails with "imported module not found", fix it
//import module elves.of.lothlorien;

import lothlorien.Celeborn;
import lothlorien.Galadriel;
import lothlorien.Haldir;

public class FarewellToLórienAfter {
    void main() {
        Haldir guardian = new Haldir();
        guardian.escort();

        Celeborn lord = new Celeborn();
        lord.offerCounsel();

        Galadriel lady = new Galadriel();
        lady.speakLightOfEärendil();
    }
}
