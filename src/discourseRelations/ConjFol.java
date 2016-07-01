package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class ConjFol {
	public static Set<String> ConjFol_set = new HashSet<String>();
	
	public static void init(){
		In in_ConjFol = new In(Config.ConjFol_input);
		System.out.println("load...ConjFol");
		while (in_ConjFol.hasNextLine()) {
			String line = in_ConjFol.readLine();
			String word = line.trim();
			ConjFol_set.add(word);
		}
		in_ConjFol.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("ConjFol:" + ConjFol_set.size());
	}
}
