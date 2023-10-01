package interfaceuser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Rendu cette classe definie le composant representant la zone de rendu !
 * @author  Elhadj Alseiny20
 */
public class Rendu extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel rendu = new JPanel(new BorderLayout());
	float zoom=1;
    public JRadioButton rendu2DBoutton, rendu3DBoutton;
    public JButton zoomBoutton, dezoomBoutton;
    public Rendu(){
        super();
        rendu3DBoutton = new JRadioButton("Rendu 3D");
        rendu2DBoutton = new JRadioButton("Rendu 2D",true);
        zoomBoutton = new JButton("+");
        dezoomBoutton = new JButton("-");
        dezoomBoutton.addActionListener((Event)-> this.zoom -= 0.02);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rendu2DBoutton);
        buttonGroup.add(rendu3DBoutton);
        JPanel choixRendu = new JPanel();
        choixRendu.add(rendu2DBoutton);
        choixRendu.add(rendu3DBoutton);
        JPanel zoomPanel = new JPanel();
        zoomPanel.add(zoomBoutton);
        zoomPanel.add(dezoomBoutton);
        rendu.setPreferredSize(new Dimension(600,600));
        rendu.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 2),"Zone de rendu"));
        rendu.add(choixRendu,BorderLayout.NORTH);
        rendu.add(zoomPanel,BorderLayout.SOUTH);
    }
    /** 
     * getter du panel scrolable contenant 
     * @return un JscrollPane contenent tout le rendu
     * */
    JScrollPane getRenduPanel(){
        JScrollPane scrollPane = new JScrollPane(rendu);
        return scrollPane;
    }


}
