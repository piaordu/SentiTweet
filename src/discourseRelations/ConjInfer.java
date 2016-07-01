package discourseRelations;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class ConjInfer {
	public static Set<String> ConjInfer_set = new HashSet<String>();
	
	public static void init(){
		In in_ConjInfer = new In(Config.ConjInfer_input);
		System.out.println("load...ConjInfer");
		while (in_ConjInfer.hasNextLine()) {
			String line = in_ConjInfer.readLine();
			String word = line.trim();
			ConjInfer_set.add(word);
		}
		in_ConjInfer.close();
		
		showInfo();
	}
	
	public static void showInfo() {
		System.out.println("ConjInfer:" + ConjInfer_set.size());
	}
}
