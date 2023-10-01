package interfaceuser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Configuration cette classe permet de modeliser la zone de saisie et configuration !
 * @author  Elhadj Alseiny20
 */

public class Configuration extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel parametrePanel,controlPane;
    private int nbRule=3;
    public JTextField axiomText,iterationText,angleText,rule1Text,rule2Text;
    public JButton generateButton;
    public ArrayList <JTextField> listeRules;
    public ArrayList <JLabel> listeLabel;

    public Configuration(){
        super();
        this.setLayout(new BorderLayout());
        this.parametrePanel = new JPanel();
        this.axiomText = new JTextField();
        this.iterationText = new JTextField();
        this.angleText = new JTextField();
        this.rule1Text = new JTextField();
        this.rule2Text = new JTextField();
        this.defaultConfiguration();
        this.controlPane = new JPanel();
        this.generateButton = new JButton("Generate");
        this.listeRules = new ArrayList<>();
        this.listeLabel = new ArrayList<>();
        this.parametrePanel.setPreferredSize(new Dimension(400,280));
        Border line = BorderFactory.createLineBorder(Color.black, 1);
        this.parametrePanel.setBorder(line);
        //
        JLabel iterationLabel = new JLabel("Iteration");
        iterationLabel.setPreferredSize(new Dimension(55,20));
        JLabel angleLabel = new JLabel("Angle");
        angleLabel.setPreferredSize(new Dimension(55,20));
        JLabel axiomLabel = new JLabel("Axiom");
        axiomLabel.setPreferredSize(new Dimension(55,20));
        JLabel rule1Label = new JLabel("Rule1");
        rule1Label.setPreferredSize(new Dimension(55,20));
        JLabel rule2Label = new JLabel("Rule2");
        rule2Label.setPreferredSize(new Dimension(55,20));
        
        this.axiomText.setPreferredSize(new Dimension(300,30));
        this.iterationText.setPreferredSize(new Dimension(300,30));
        this.angleText.setPreferredSize(new Dimension(300,30));
        this.rule1Text.setPreferredSize(new Dimension(300,30));
        this.rule2Text.setPreferredSize(new Dimension(300,30));
        this.listeRules.add(this.rule1Text);
        this.listeRules.add(this.rule2Text);
        //creation zone axiom
        JPanel axiomPanel = new JPanel();
        axiomPanel.add(axiomLabel);
        axiomPanel.add(axiomText);
        //creation zone des regles par defaut
        JPanel rule1Panel = new JPanel();
        rule1Panel.add(rule1Label);
        rule1Panel.add(rule1Text);
        JPanel rule2Panel = new JPanel();
        rule2Panel.add(rule2Label);
        rule2Panel.add(rule2Text);
        //creation zone iteration
        JPanel iterationPanel = new JPanel();
        iterationPanel.add(iterationLabel);
        iterationPanel.add(iterationText);
        //creation zone d'entree Angle
        JPanel anglePanel = new JPanel();
        anglePanel.add(angleLabel);
        anglePanel.add(angleText);
        //Creation des boutons de controle 
        this.generateButton.setPreferredSize(new Dimension(120,30));
        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(120,30));
        clearButton.addActionListener(this::clearListener);
        //
        JButton ajoutButton = new JButton("new rules");
        ajoutButton.setPreferredSize(new Dimension(120,30));
        ajoutButton.addActionListener(this::ajoutListener);
        //disposition des panel
        this.controlPane.add(generateButton);
        this.controlPane.add(clearButton);
        this.controlPane.add(ajoutButton);
        //
        this.parametrePanel.add(iterationPanel);
        this.parametrePanel.add(anglePanel);
        this.parametrePanel.add(axiomPanel);
        this.parametrePanel.add(rule1Panel);
        this.parametrePanel.add(rule2Panel);
        //
        JScrollPane jscp = new JScrollPane(parametrePanel);
        this.add(jscp,BorderLayout.CENTER);
        this.add(controlPane,BorderLayout.SOUTH);
    }
    /** 
     * cette fonction definie une configuration par defaut
     */
    public void defaultConfiguration(){
        this.iterationText.setText("6");
    	this.angleText.setText("25.7");
        this.axiomText.setText("X");
        this.rule1Text.setText("X=F[+X][-X]FX");
        this.rule2Text.setText("F=FF");
    }
    /** 
     * fonction de reset des champs de parametrage 
     */
    public void clearListener(ActionEvent event){
    	this.iterationText.setText("");
    	this.axiomText.setText("");
    	this.angleText.setText("");
    	this.rule1Text.setText("");
    	this.rule2Text.setText("");
        for (JTextField contenu: listeRules){
            contenu.setText("");
        }
    }
    /**fonction d'ajoue de nouvelle regle
     * cette fonction ajoute une regles et apllique lensemble des traitement associé 
     * ainsi qu'un bouton de remove ecoutable afin de pouvoir le supprimer 
     */
    public void ajoutListener(ActionEvent event){
        //ajoutListener();
        int numberRules = listeRules.size()+1;
        JButton button = new JButton("X");
        button.setBackground(Color.gray);
        JLabel ruleLabel = new JLabel("Rule"+numberRules);
        ruleLabel.setPreferredSize(new Dimension(55,20));
        JTextField ruleText = new JTextField();
        ruleText.setPreferredSize(new Dimension(260,30));
        this.listeRules.add(ruleText);
        JPanel rulePanel = new JPanel();
        //traitement associé au nouveau regle ajouté lors de l'ajout
        button.addActionListener((e) -> {
            if (this.listeLabel != null)
            	this.listeLabel.remove(ruleLabel);
            rulePanel.getParent().remove(rulePanel);
            this.listeRules.remove(ruleText);
            this.parametrePanel.setPreferredSize(new Dimension(this.parametrePanel.getPreferredSize().width,parametrePanel.getPreferredSize().height-(rulePanel.getPreferredSize().height+3)));
            this.parametrePanel.repaint();
            this.parametrePanel.setVisible(false);
            this.parametrePanel.setVisible(true);
            for (JLabel label : this.listeLabel){
                label.setText("Rule"+nbRule);
                this.nbRule++;
            }
            this.nbRule = 3;
        });
        //mise a jour du panel des parmatre apres l'ajout d'une regle pour le rendre dinamique
        rulePanel.add(ruleLabel);
        rulePanel.add(ruleText);
        rulePanel.add(button);
        this.listeLabel.add(ruleLabel);
        this.parametrePanel.add(rulePanel);
        this.parametrePanel.revalidate();
        this.parametrePanel.repaint();
        this.parametrePanel.setVisible(false);
        this.parametrePanel.setVisible(true);
        this.parametrePanel.setPreferredSize(new Dimension(this.parametrePanel.getPreferredSize().width,this.parametrePanel.getPreferredSize().height+rulePanel.getPreferredSize().height+3));
        this.parametrePanel.revalidate();
        this.parametrePanel.repaint();
    }
}
