package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class Neg {
	public static Set<String> Neg_set = new HashSet<String>();
	
	public static void init(){
		In in_Neg = new In(Config.Neg_input);
		System.out.println("load...Neg");
		while (in_Neg.hasNextLine()) {
			String line = in_Neg.readLine();
			String word = line.trim();
			Neg_set.add(word);
		}
		in_Neg.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("Neg:" + Neg_set.size());
	}
}
