package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class WeakMod {
	public static Set<String> WeakMod_set = new HashSet<String>();
	
	public static void init(){
		In in_WeakMod = new In(Config.WeakMod_input);
		System.out.println("load...WeakMod");
		while (in_WeakMod.hasNextLine()) {
			String line = in_WeakMod.readLine();
			String word = line.trim();
			WeakMod_set.add(word);
		}
		in_WeakMod.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("WeakMod:" + WeakMod_set.size());
	}
}
