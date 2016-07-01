package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class StrongMod {
	public static Set<String> StrongMod_set = new HashSet<String>();
	
	public static void init(){
		In in_StrongMod = new In(Config.StrongMod_input);
		System.out.println("load...StrongMod");
		while (in_StrongMod.hasNextLine()) {
			String line = in_StrongMod.readLine();
			String word = line.trim();
			StrongMod_set.add(word);
		}
		in_StrongMod.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("StrongMod:" + StrongMod_set.size());
	}
}
