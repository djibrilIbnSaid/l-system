
package interfaceuser;

import javax.swing.*;
import java.awt.*;

/**
 * Menu cette classe definie le composant representant le menu!
 * @author  Elhadj Alseiny20
 */
public class Menu extends JToolBar{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JComboBox <String> lSystemsBouton ;
    JButton aideButton;
    public Menu (){
        //creation de la bare de menu
        JLabel exemple = new JLabel("L_System : ");
        //creation du bouton LSystem
        String[] choices = {"choix Lsytéme","ARBRE 1","ARBRE 2", "ARBRE 3","ARBRE 4","ARBRE 5"};
        lSystemsBouton = new JComboBox<>(choices);
        lSystemsBouton.setPreferredSize(new Dimension(140,30));
        this.add(exemple);
        this.add(lSystemsBouton);
        //creation du bouton exitButton
        aideButton = new JButton("Aide");
        aideButton.setPreferredSize(new Dimension(110,30));
        this.add(aideButton);

    }
}
