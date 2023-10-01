package testparser;
import junit.framework.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import parser.Parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.RunRules;

import parser.Rule;



public class ParserTest extends TestCase{

    private Parser parser;

    @Test
    public void testParserNotEmpty(){
    	 Rule rule1 =new Rule("X","F[+X][-X]FX");
    	 Rule rule2=new Rule("F","FF");
         ArrayList<Rule>  rules =new ArrayList<>();
         rules.add(rule1);
         rules.add(rule2);
         
         this.parser=new Parser("X",rules,2);
        assertNotNull("Parser should not be null ",this.parser);
    }
    
    @Test
    public void testIsCorrect() {
    	 Rule rule1 =new Rule("F","FF-[-F+F+]+[+F-F-F]");
    	 ArrayList<Rule>  rules =new ArrayList<>();
         rules.add(rule1);
         
         Parser parser1=new Parser("F", rules, 3);
         assertTrue(parser1.isCorrect());
         
         parser1.setAxiom("");
         assertFalse(parser1.isCorrect());
         
         parser1.setNbIteration(-1);
         assertFalse(parser1.isCorrect());
         
         parser1.setNbIteration(4);
         parser1.setAxiom("F");
         assertTrue(parser1.isCorrect());
    }
    @Test
    public void testGeneration(){
    	 Rule rule1 =new Rule("X","F[+X][-X]FX");
    	 Rule rule2=new Rule("F","FF");
         ArrayList<Rule>  rules =new ArrayList<>();
         rules.add(rule1);
         rules.add(rule2);
         
         this.parser=new Parser("X",rules,3);
         assertEquals(" Equal","FFFF[+FF[+F[+X][-X]FX][-F[+X][-X]FX]FFF[+X][-X]FX][-FF[+F[+X][-X]FX]"
        		+"[-F[+X][-X]FX]FFF[+X][-X]FX]FFFFFF[+F[+X][-X]FX][-F[+X][-X]FX]FFF[+X][-X]FX",this.parser.generation());
            
    }

    @Test
    public void testNulParser() {
    	 Rule rule1 =new Rule("F","FF-[-F+F+]+[+F-F-F]");
         ArrayList<Rule>  rules =new ArrayList<>();
         rules.add(rule1);
         
         this.parser=new Parser("X",rules,3);
    	 this.parser=null; 
         assertNull("Parser should not be null ",parser);   
    }

}