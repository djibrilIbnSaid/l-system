package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import utils.PointPosition;



/**
 * Rendu2D cette classe permet d'afficher le rendu en 2D !
 * 
 * @author  diallo-djiguine-diallo-diallo
 * @version 1.0
 * @since   2022-04-03
 */
public class Rendu2D extends JComponent {
	private int width;
	private int height;
	private String code;
	private float angle;
	
	/**
	 * Le constructeur du rendu 2D.
	 * 
	 * @param width la largeure du panel, type Integer.
	 * @param height la hauteur du panel, type Integer.
	 * @param code le code de lsysteme, type String.
	 * @param angle l'angle des branches, type float.
	 * @return void.
	 */
	public Rendu2D(int width, int height, String code, float angle) {
		this.width = width;
		this.height = height;
		this.code = code;
		this.angle = angle;
	}
	
	/**
	 * Cette fonction permet d'afficher l'arbre en 2D
	 * 
	 * F ou X (lettre) : Se déplacer d’un pas unitaire (∈ V) ;
 	* + : Tourner à gauche d’angle α (∈ S) ;
 	* - : Tourner à droite d’un angle α (∈ S) ;
 	* [ : Sauvegarder la position courante (∈ S) ;
 	* ] : Restaurer la dernière position sauvée (∈ S).
	 * 
	 * @param g le paramettre et de type Graphics.
	 * @return void.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		int taille = 40;
		float angleLocal = 0;
		ArrayList<PointPosition> savePositions = new ArrayList<>();
		int startX = this.width;
		int startY = this.height;
		int endX = 0;
		int endY = 0;

		if (this.code.length() > 100000) {
			g2d.translate(width, height+height/2);
			g2d.scale(0.04, 0.04);
			g2d.translate(-width, -height+height/2);
		} else if (this.code.length() > 2000) {
			g2d.translate(width, height+height/2);
			g2d.scale(0.1, 0.1);
			g2d.translate(-width, -height+height/2);
		} else if (this.code.length() > 1000) {
			g2d.translate(width, height+height/2);
			g2d.scale(0.2, 0.2);
			g2d.translate(-width, -height+height/2);
		} else if (this.code.length() > 200) {
			g2d.translate(width, height);
			g2d.scale(0.3, 0.3);
			g2d.translate(-width, -height);
		}  else if (this.code.length() > 100) {
			g2d.translate(width, height);
			g2d.scale(0.4, 0.4);
			g2d.translate(-width, -height);
		} else if (this.code.length() > 50) {
			g2d.translate(width, height);
			g2d.scale(0.7, 0.7);
			g2d.translate(-width, -height);
		}

		String[] tabCode = this.code.split("");
		for(String c : tabCode) {
			if(c.equals("+")) {
				angleLocal += this.angle;
			} else if (c.equals("-")) {
				angleLocal -= this.angle;
			} else if (c.equals("[")) {
				savePositions.add(new PointPosition(endX, endY, angleLocal));
			} else if (c.equals("]")) {
				if (savePositions.size() > 0) {
					PointPosition sp = savePositions.remove(savePositions.size()-1);
					endX = sp.getX();
					endY = sp.getY();
					angleLocal = sp.getAngle();
				}
			} else if (Character.isLetter(c.charAt(0))) {
				if (endX != 0 && endY != 0) {
					startX = endX;
					startY = endY;
				}
				endX = (int) (startX - taille * Math.sin(Math.toRadians(angleLocal)));
				endY = (int) (startY - taille * Math.cos(Math.toRadians(angleLocal)));
				g2d.drawLine(startX, startY, endX, endY);
			}
		}
	}
}
