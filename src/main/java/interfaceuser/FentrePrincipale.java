package interfaceuser;

import graphique.Rendu2D;
import graphique.Rendu3D;
import parser.Parser;
import parser.Rule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * FentrePrincipale cette classe permet de rassembler toute les composante graphique!
 * et etablie la liaison entre toute les classe (parser, rendu2D, rendu3D, ..)
 * @author  Elhadj Alseiny20
 */
public class FentrePrincipale extends JFrame {
    private static final long serialVersionUID = 7376825297884956163L;
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWith = (tailleEcran.width*2/3)+150;
    int screenheight = (tailleEcran.height*2/3)+100;
    
    private final Menu menu;
    private final Configuration zoneConfiguration;
    private final Rendu zoneRendu;
    private Rendu2D rendu2D;
    private Rendu3D rendu3D;
    public FentrePrincipale(){ 
        super("L-Systeme");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWith,screenheight);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        this.menu = new Menu();
        this.zoneRendu = new Rendu();
        this.zoneConfiguration = new Configuration();
        contentPane.add(menu, BorderLayout.NORTH);
        contentPane.add(createPage(),BorderLayout.CENTER);
        this.zoneConfiguration.generateButton.addActionListener(this::generateListener);
        this.menu.lSystemsBouton.addActionListener(this::l_systelButtonListener);
        this.zoneRendu.rendu2DBoutton.addActionListener(this::l_systelButtonListener);
        this.zoneRendu.rendu3DBoutton.addActionListener(this::l_systelButtonListener);
        this.menu.aideButton.addActionListener((Event)->new Aide());
    }

    /**
     * fonction de creation du contenue principale du Frame */
    private JPanel createPage(){
        JPanel page = new JPanel(new BorderLayout(50, 30));
        page.setPreferredSize(new Dimension(300,200));
        page.add(this.zoneConfiguration,BorderLayout.WEST);
        page.add(this.zoneRendu.getRenduPanel(),BorderLayout.CENTER);
        //creation de deux panel vide pour rejoudre le probleme des margin
        JPanel west = new JPanel();
        JPanel south = new JPanel();
        west.setPreferredSize(new Dimension(20,0));
        south.setPreferredSize(new Dimension(1,0));
        page.add(west,BorderLayout.EAST);
        page.add(south,BorderLayout.SOUTH);
        return page;
    }
    /**fonction de genreation de rendu a partir des parametre define et du mode rendu selectioné (3D ou 2D) */
    void generateListener (ActionEvent event){
    	//recuperation du contenue de parmatrage et parsage 
        ArrayList<Rule> liste = new ArrayList<>();
        float angle = Float.parseFloat(this.zoneConfiguration.angleText.getText());
        int iteration = Integer.parseInt( this.zoneConfiguration.iterationText.getText());
        for (JTextField text: this.zoneConfiguration.listeRules){
            String[] contenu = text.getText().split("=");
            if (contenu.length==2){
                liste.add(new Rule(contenu[0],contenu[1]));
            }
        }
        /** mise a jour du zone de rendu choix  et generationn du type de rendu */
        Parser parser = new Parser(this.zoneConfiguration.axiomText.getText(), liste, iteration);
        if (this.rendu2D != null){
            this.rendu2D.repaint();
            this.zoneRendu.rendu.remove(this.rendu2D);
            this.zoneRendu.rendu.setVisible(false);
        }
        
        if (this.rendu3D != null){
            this.rendu3D.repaint();
            this.zoneRendu.rendu.remove(this.rendu3D.glCanvas);
            this.zoneRendu.rendu.setVisible(false);
        }
        if (this.zoneRendu.rendu2DBoutton.isSelected()) {
	        this.zoneRendu.rendu.setPreferredSize(new Dimension(600,600));
		this.rendu2D= new Rendu2D(this.zoneRendu.rendu.getWidth()/2,350, parser.generation(),angle);
	        this.rendu2D.repaint();
	        this.rendu2D.setVisible(false);
	        this.rendu2D.setVisible(true);
	        this.zoneRendu.rendu.add(this.rendu2D,BorderLayout.CENTER);
        }
        else {
	        this.rendu3D = new Rendu3D(parser.generation(),angle);
	        this.zoneRendu.rendu.setPreferredSize(new Dimension(600,700));
	        this.zoneRendu.zoomBoutton.addActionListener((Event)-> rendu3D.scale +=0.01);
	        this.zoneRendu.dezoomBoutton.addActionListener((Event)-> rendu3D.scale -=0.01);
        	this.zoneRendu.rendu.add(this.rendu3D.glCanvas,BorderLayout.CENTER);
        }
        this.zoneRendu.rendu.repaint();
        this.zoneRendu.rendu.setVisible(false);
        this.zoneRendu.rendu.setVisible(true);
    }
    /**defintion des traitement et quelque regle par defaut sur le menu deroulant lSystem */
    private void l_systelButtonListener (ActionEvent event){
        if(menu.lSystemsBouton.getSelectedIndex() != 0){
		for (JTextField regle: this.zoneConfiguration.listeRules){
		    regle.setText("");
		}
	}
       
        /** definition des parmatre par defaut */
        if(menu.lSystemsBouton.getSelectedIndex() ==1){
            this.zoneConfiguration.defaultConfiguration();
            if(this.zoneRendu.rendu3DBoutton.isSelected()) {
            	this.zoneConfiguration.iterationText.setText("4");
                this.zoneConfiguration.rule1Text.setText("X=F[+X][-X]F>[HX][BX]F>X");

            }
        }
        /** quelque regles d'arbre adapter au 3D et 2D */       
        else if(this.menu.lSystemsBouton.getSelectedIndex() ==2){
            this.zoneConfiguration.iterationText.setText("5");
            this.zoneConfiguration.angleText.setText("20");
            this.zoneConfiguration.axiomText.setText("X");
            this.zoneConfiguration.rule2Text.setText("F=FF");
            if(this.zoneRendu.rendu2DBoutton.isSelected()) {
                this.zoneConfiguration.rule1Text.setText("X=F[+X]F[-X]+X");
            }else {
                this.zoneConfiguration.rule1Text.setText("X=F[+X]F[-X][BX]<+X");
            }
        }
        
        else if(this.menu.lSystemsBouton.getSelectedIndex() ==3){
            this.zoneConfiguration.iterationText.setText("5");
            this.zoneConfiguration.angleText.setText("22.5");
            this.zoneConfiguration.axiomText.setText("X");
            this.zoneConfiguration.rule2Text.setText("F=FF");
            if(this.zoneRendu.rendu2DBoutton.isSelected()) {
                this.zoneConfiguration.rule1Text.setText("X=F-[[X]+X]+F[+FX]-X");
            }else {
                this.zoneConfiguration.iterationText.setText("4");
                this.zoneConfiguration.rule1Text.setText("X=F-[[X]+X]+F>>[+FX]-X");
            }
        }
        
        else if(this.menu.lSystemsBouton.getSelectedIndex() ==4){
            this.zoneConfiguration.angleText.setText("25.7");
            this.zoneConfiguration.axiomText.setText("F");
            if(this.zoneRendu.rendu2DBoutton.isSelected()) {
                this.zoneConfiguration.iterationText.setText("3");
                this.zoneConfiguration.rule1Text.setText("F=F[+F]F[-F]F");
            }else {
                this.zoneConfiguration.iterationText.setText("4");
                this.zoneConfiguration.rule1Text.setText("F=F[+F]F>[-F]F");
            }
        }
        else if(menu.lSystemsBouton.getSelectedIndex() ==5){
            this.zoneConfiguration.angleText.setText("20");
            this.zoneConfiguration.axiomText.setText("X");
            this.zoneConfiguration.rule2Text.setText("F=FF");
            if(this.zoneRendu.rendu2DBoutton.isSelected()) {
                this.zoneConfiguration.iterationText.setText("5");
                this.zoneConfiguration.rule1Text.setText("X=F+[-F-XF-X][--XF[+X]][++F-X]");
            }else {
                this.zoneConfiguration.angleText.setText("30");
                this.zoneConfiguration.iterationText.setText("4");
                this.zoneConfiguration.rule1Text.setText("X=[BFX]>[HFX]>[BFX]>[HFX]");
                this.zoneConfiguration.axiomText.setText("FX");

            }
        }
    }
}
