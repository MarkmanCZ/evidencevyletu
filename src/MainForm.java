import cz.markman.menus.InputWorker;
import cz.markman.menus.Menu;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame {
    private JPanel mainWindow;
    private JTextField delNumInput;
    private JButton smažButton;
    private JTextArea dataArrea;

    private JMenuBar menuBar;

    //pokud nepouzijes moji tridu tak
    //private JMenu menu;
    private Menu menu;
    private JMenuItem load;
    private JMenuItem refresh;
    private ArrayList<Cyklovylet> cyklovylets = new ArrayList<>();
    private JFileChooser fileChooser;

    private File currentFile;

    public MainForm() {
        //nastaveni itemu

        //inicializace menu baru
        menuBar = new JMenuBar();

        //inicializace menu custom trida extenduje JMenu a pridava jednu metodu
        menu = new Menu("Soubor");
        //inicializace menu itemu
        load = new JMenuItem("Načti");
        refresh = new JMenuItem("Refresh");

        //custom metoda addMenus zapomoci item, item, item...
        menu.addMenus(load, refresh);

        /*bez custom tridy
        menu.add(load);
        menu.add(refresh);
         */

        menuBar.add(menu);

        //listenery
        //zobrazi dialog filechooser a nacte soubor
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pred nactenim novych dat smaz stary zaznam, z textarea (z arraylistu)
                cyklovylets.removeAll(cyklovylets);

                fileChooser = new JFileChooser();

                int result = fileChooser.showDialog(mainWindow, "Vybrat");
                if(result == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(mainWindow,"Nebyl vybrán žádný soubor...","Error", JOptionPane.ERROR_MESSAGE);
                }else if(result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        currentFile = file;
                        loadData();
                    }catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(mainWindow,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(mainWindow,"Chyba...","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //nacti veci do textarea
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   loadData();
               }catch(FileNotFoundException ex) {
                   ex.printStackTrace();
               }
            }
        });
        //smaze zaznam
        smažButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(delNumInput.getText().length() != 0) {
                    int index = Integer.parseInt(delNumInput.getText())-1;
                    cyklovylets.remove(cyklovylets.get(index));

                    dataArrea.setText("");
                    fillTextArrea();
                    InputWorker.resetInputs(delNumInput);
                }else {
                    JOptionPane.showMessageDialog(mainWindow,"Vyplň číslo indexu řádku!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //setJMenuBar() prida JMenuBar
        this.setJMenuBar(menuBar);

        try {
            currentFile = new File("U:\\java\\evidence\\test.txt");
            loadData();
        }catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(mainWindow,ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void loadData() throws FileNotFoundException {
        if(currentFile != null) {
            //nacti z current file
            cyklovylets.removeAll(cyklovylets);
            Scanner scanner = new Scanner(currentFile);
            if(scanner.hasNextLine()) {
                int i = 1;
                while(scanner.hasNextLine()) {
                    String radek = scanner.nextLine();
                    String[] data = radek.split(",");

                    Cyklovylet vylet = new Cyklovylet(data[0], Double.parseDouble(data[1]), LocalDate.parse(data[2]));

                    cyklovylets.add(vylet);
                }
            }else {
                JOptionPane.showMessageDialog(mainWindow,"Soubor je prazndy!", "Error", JOptionPane.ERROR_MESSAGE);
            }


            fillTextArrea();
        }else {
            JOptionPane.showMessageDialog(mainWindow,"Problem!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void fillTextArrea() {
        dataArrea.setText("");

        for(int i = 0;i < cyklovylets.size();i++) {
            dataArrea.append((i+1)+". "+cyklovylets.get(i).getCil()+" ("+cyklovylets.get(i).getDelka()+" km)"+"\n");
        }
    }

    public JPanel getMainWindow() {
        return mainWindow;
    }

}
