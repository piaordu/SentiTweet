package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class Conditionals {
	public static Set<String> Conditionals_set = new HashSet<String>();
	
	public static void init(){
		In in_Conditionals = new In(Config.Conditionals_input);
		System.out.println("load...Conditionals");
		while (in_Conditionals.hasNextLine()) {
			String line = in_Conditionals.readLine();
			String word = line.trim();
			Conditionals_set.add(word);
		}
		in_Conditionals.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("Conditionals:" + Conditionals_set.size());
	}
}
