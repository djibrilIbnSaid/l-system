package parser;
import java.util.ArrayList;
public class Demo{


	public static void main(String args []){
	
		Rule rule1=new Rule("A","AB+");
		Rule rule2=new Rule("B","A");
		Rule rule3=new Rule("X","F[+X][-X]FX");
		Rule rule4=new Rule("F","FF");
		ArrayList<Rule> list=new ArrayList<>();
		// list.add(rule1);
		// list.add(rule2);
		list.add(rule3);
		list.add(rule4);
		
		
		
		Parser parser1=new Parser("A",list,5);
		Parser parser2=new Parser("X",list,5);
		
		
		System.out.println("Axiome:       "+parser1.getAxiom());
		parser1.generation();
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Axiome:        "+parser2.getAxiom());
		parser2.generation();
		
	}



}
