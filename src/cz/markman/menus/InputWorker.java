package cz.markman.menus;

import javax.swing.*;

public class InputWorker {

    public static void resetInputs(JTextField... inputs) {
        for (JTextField input : inputs) {
            input.setText("");
        }
    }

    public static void resetCheckBoxes(JCheckBox... boxes) {
        for (JCheckBox box : boxes) {
            box.setSelected(false);
        }
    }

}
