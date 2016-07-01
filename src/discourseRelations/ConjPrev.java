package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class ConjPrev {
	public static Set<String> ConjPrev_set = new HashSet<String>();
	
	public static void init(){
		In in_ConjPrev = new In(Config.ConjPrev_input);
		System.out.println("load...ConjPrev");
		while (in_ConjPrev.hasNextLine()) {
			String line = in_ConjPrev.readLine();
			String word = line.trim();
			ConjPrev_set.add(word);
		}
		in_ConjPrev.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("ConjPrev:" + ConjPrev_set.size());
	}
}
