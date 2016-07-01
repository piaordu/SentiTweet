package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Tweet;

public class BagOfWords {
	public static List<String> dict = new ArrayList<String>();
	
	static{
		generateDictionary();
	}
	public static void generateDictionary() {
		List<Tweet> tweetList = PreProcess.loadTrainCorpus();
		Set<String> dict_set = new HashSet<String>();
		for(Tweet tweet:tweetList) {
			List<String> tok_list = tweet.getTokenList();
			dict_set.addAll(tok_list);
		}
		dict.addAll(dict_set);
		//System.out.println(dict);
	}
	
}
