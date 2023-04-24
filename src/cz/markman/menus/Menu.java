package cz.markman.menus;

import javax.swing.*;

public class Menu extends JMenu {

    public Menu() {
        super();
    }

    public Menu(String s) {
        super(s);
    }

    public void addMenus(JMenuItem... itemsToAdd) {
        for(JMenuItem item : itemsToAdd) {
            getPopupMenu().add(item);
        }
    }

}
