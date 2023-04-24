import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setContentPane(form.getMainWindow());
        form.setSize(500, 350);
        form.setTitle("Cyklovýlety");
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}