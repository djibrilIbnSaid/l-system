package interfaceuser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Aide cette classe definie des information et mode d'emploie de l'interface !
 * @author  Elhadj Alseiny20
 */
public class Aide extends JFrame {
    public Aide (){
        super("Aide");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        JTextPane jTextPane = new JTextPane();
        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        this.add(jScrollPane);
        this.setVisible(true);
        Style style2 = jTextPane.addStyle("style2", jTextPane.getStyle(""));
        StyleConstants.setFontSize(style2, 18);
        String s1 = "\tCette application permet de générer un l-système végétal particulièrement\n" +
                "            un arbre enCHOICE applicant un système de reécriture a un ensemble de règles .     \t\t\t\t\tdéfinies.\n" +
                "\t\t\t \t\n" +
                "\n" +
                "Explication des champs:\n" +
                "#- dans la zone de paramétrage à gauche \n" +
                "\taxiom    :  point de depart de l'état initial\n" +
                "\tAngle     :  un floatant representant une inité de rotation pourt un axe donné\n" +
                "\tItération : Entier positif representant le nombre d'application des regles\n" +
                "\tRègle     : contraintes qui seront appliquées à chaque itération\n" +
                "\n" +
                "#- Boutons de controles:\n" +
                "\tGenerate  : un bouton permettant de visualiser un l-système\n" +
                "\tNew Rules: permet d'ajouter un champ de règle au tant que souhaiter \n" +
                "            (une bare defilente serait mise automatiquement pour faciliter la navigation)\n" +
                "\n" +
                "# Menu\n" +
                "\tL-Système: liste déroulante contenant des exemples prédéfinie \n" +
                "\ten 2D et 3DCHOICE suivant le mode de rendu selectionné dans la zone de rendu.\n" +
                "\n" +
                "# Zone de rendu\n" +
                "\t- Deux cases à cocher permettant de selectionner le mode de rendu souhaité\n" +
                "\t- le rendu 3D est mini de la possibilté de zoomer et dezoomer à l'aide des \n" +
                "\tboutons (+ et -) ainsi qu'une possibilité de se déplacer autour de l'arbre à \n" +
                "\tl'aide de cliques glisse avec la sourie dans la zone\n" +
                "\n" +
                "# Interprétation des instructions parametrage\n" +
                "\t+ : Tourner à gauche d’angle α (∈ S) ;\n" +
                "\t- : Tourner à droite d’un angle α (∈ S) ;\n" +
                "\t[ : Sauvegarder la position courante (∈ S) ;\n" +
                "\t] : Restaurer la dernière position sauvée (∈ S).\n" +
                "\tF ainsi que tout autre lettre non cité : Se déplacer d’un pas unitaire (∈ V) ;\n" +
                "\n" +
                "     \t-Ajouter à ces instructions ci-déssus pour le rendue 3D on a:\n" +
                "\n" +
                "\tB: S’incliner vers (tangage) le bas d’un angle δ\n" +
                "\tH: S’incliner vers (tangage) le haut d’un angle δ\n" +
                "\t< Rouler à gauche (roulis) d’un angle δ\n" +
                "\t> Rouler à droite (roulis) d’un angle δ\n" +
                "\t\n" +
                "@Ce projet a été réalisé par:\n" +
                "- Elhadj Alseiny DIALLO     \n" +
                "- Abdoulaye Djibril DIALLO  \n" +
                "- Mamady DJIGUINE           \n" +
                "- Mamadou Alpha DIALLO   ";
        
        StyledDocument sDoc = (StyledDocument)jTextPane.getDocument();
        jTextPane.setEnabled(false);
        try {
            sDoc.insertString(0, s1, style2);
        } catch (BadLocationException e) { }
    }
}
