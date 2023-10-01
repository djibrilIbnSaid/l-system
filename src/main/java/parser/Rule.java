package parser;

import java.lang.Character;

public class Rule{


	private String character;
	private String itValues;
	
	/**
	 * Constructeur de la classe Rule permettant de créer les règles
	 * @param letter
	 * @param val
	 * @Ensure character=letter et itValues=val
	 */
	public Rule(String letter ,String val){
		
		this.character=letter;
		this.itValues=val;
	
	}
	
	public String getCharacter(){
	
		return this.character;
	}
	
	public String getItValues(){
	
		return this.itValues;
	}
	
	public void setCharacter(String c){
		this.character=c;
	}
	
	public void setItValues(String val){
		this.itValues=val;
	}

	/**
	 * Cette fonction vérifie  si la règle est applicable au caractère courant
	 * @param un caractère c 
	 * @return Vrai Si la règle est applicable au caractère et Faux Sinon.
	 */
	public boolean compare(Character c){
		return Character.toString(c).equals(this.character);
	}
}
