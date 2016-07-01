package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class MidPrev {
	public static Set<String> MidPrev_set = new HashSet<String>();
	
	public static void initial() {
		In in_MidPrev = new In(Config.MidPrev_input);
		
		while(in_MidPrev.hasNextLine()) {
			String line = in_MidPrev.readLine();
			line = line.trim();
			MidPrev_set.add(line);
		}
		
		in_MidPrev.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("MidPrev:" + MidPrev_set.size());
	}

}
