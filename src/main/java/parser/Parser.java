package parser;

import java.util.ArrayList;

public class Parser{


	private String axiom;
	private ArrayList<Rule> rules;
	private int nbIteration;
	
	/** A constructor initialising the declared attributs 
	@Param1 base the set value of the axiom 
	@Param2 A list of applicable rules to the object
	Ensure:Initialized the attributs 
	*/
	public Parser(String base,ArrayList<Rule> rule,int iteration){
		this.axiom=base;
		this.rules=rule;
		this.nbIteration=iteration;
	}
	
	public String getAxiom(){
		return this.axiom;
	}
	
	public ArrayList<Rule> getRules(){
		return this.rules;
	}
	
	public int nbIteration(){
		return this.nbIteration;
	}
	
	public void setAxiom(String base){
		this.axiom=base;
	}
	public void setNbIteration(int nombre){
		this.nbIteration=nombre;
	}
	public void setRules(ArrayList<Rule> rule) {
		this.rules=rule;
	}
	
	/**Cette fonction vérifie si l'axiom est non vide et si le nombre d'itération 
	 * est supérieur à 0 
	 * @return true si l'axiom est non vide et si le nombre d'itération  est supérieur à 0
	 * sinon false 
	 */
	public boolean isCorrect() {
		if((this.axiom.isEmpty())||(this.nbIteration<=0))
			return false;
		return true;
	}
	/**
	 * Cette fonction permet de générer un mot de code 
	 * @REQUIRED l'axiom ,règles et le nombre d'itérations soient initialisés
	 * Ensures :retourne après toutes les itérations une chaine les conténant le resultat de l'application des règles . 
	 * 
	 A function simulating a parser after the axiom ,rule and number of iteration has been giving by a user 
	 inside the constructor above.  
	 Ensure: return a string containing the result of rules application according to the number of the iterations being given;
	 */
	public String generation(){
		
		if(isCorrect()) {
			for (int i=0 ;i<this.nbIteration ;i++){
				String production="";
				for(int j=0;j<this.axiom.length();++j){
					boolean hasBeenUpdated=false;
					for(Rule r:rules){
						if(r.compare(this.axiom.charAt(j))){
							hasBeenUpdated=true;
							production+=r.getItValues();
							break;
						}
					}
					if(!hasBeenUpdated){
						production+=this.axiom.charAt(j);
					}
				}
				this.axiom=production;
			}
			
		    return this.axiom;
		}else
			return "";
	}




}
