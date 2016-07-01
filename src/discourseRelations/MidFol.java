package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class MidFol {
	public static Set<String> MidFol_set = new HashSet<String>();
	
	public static void initial() {
		In in_MidFol = new In(Config.MidFol_input);
		
		while(in_MidFol.hasNextLine()) {
			String line = in_MidFol.readLine();
			line = line.trim();
			MidFol_set.add(line);
		}
		
		in_MidFol.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("MidFol:" + MidFol_set.size());
	}

}
