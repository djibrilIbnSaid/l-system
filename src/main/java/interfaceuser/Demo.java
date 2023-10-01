package interfaceuser;


import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Demo cette classe permet l'instancification de la fenetre principale !
 * @author  Elhadj Alseiny20
 */
public class Demo {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        FentrePrincipale accueil = new FentrePrincipale();
        accueil.setVisible(true);
    }
}
