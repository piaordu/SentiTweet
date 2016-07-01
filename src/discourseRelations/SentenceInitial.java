package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class SentenceInitial {
	public static Set<String> SentenceInitial_set = new HashSet<String>();
	
	public static void initial() {
		In in_SentenceInitial = new In(Config.SentenceInitial_input);
		System.out.println("load...SentenceInitial");
		
		while(in_SentenceInitial.hasNextLine()) {
			String line = in_SentenceInitial.readLine();
			line = line.trim();
			SentenceInitial_set.add(line);
		}
		
		in_SentenceInitial.close();
		
		showInfo();
	}
	
	public static void showInfo(){
		System.out.println("SentenceInitial:" + SentenceInitial_set.size());
	}
}
