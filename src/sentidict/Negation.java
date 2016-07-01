package sentidict;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class Negation {
	public static Set<String> negationSet = new HashSet<String>();
	
	public static void init(){
		In in = new In(Config.Negation_input);
		while(in.hasNextLine()){
			String word = in.readLine();
			word = word.trim();
			negationSet.add(word);
		}
		showDictInfo();
	}
	
	public static void showDictInfo(){
		System.out.println(String.format("There're %d negation words", negationSet.size()));
	}
	
	public static void main(String[] args){
		Negation.init();
	}
}
