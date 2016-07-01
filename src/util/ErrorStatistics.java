package util;

import java.util.ArrayList;
import java.util.List;

import model.Tweet;
import config.Config;
import edu.princeton.cs.introcs.In;

public class ErrorStatistics {
	//输出列表的值
	public static void print_list(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		List<String> result = new ArrayList<String>();
		In result_in = new In(Config.Svm_Out_new);
		while (result_in.hasNextLine()) {
			String line = result_in.readLine();
			result.add(line.trim());
		}
		
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets = PreProcess.loadTestCorpus();
		
		//error index
		List<Integer> pos_to_neu = new ArrayList<Integer>();
		List<Integer> neg_to_neu = new ArrayList<Integer>();
		List<Integer> pos_to_neg = new ArrayList<Integer>();
		List<Integer> neg_to_pos = new ArrayList<Integer>();
		List<Integer> neu_to_pos = new ArrayList<Integer>();
		List<Integer> neu_to_neg = new ArrayList<Integer>();
		
		for (int i = 0; i < result.size(); i++) {
			int index = i + 1;
			if (tweets.get(i).polarity.equals("positive")) {
				if (result.get(i).equals("0.0")) {
					pos_to_neu.add(index);
				} else if (result.get(i).equals("-1.0")) {
					pos_to_neg.add(index);
				}
			} else if (tweets.get(i).polarity.equals("negative")) {
				if (result.get(i).equals("0.0")) {
					neg_to_neu.add(index);
				} else if (result.get(i).equals("1.0")) {
					neg_to_pos.add(index);
				}
			} else if (tweets.get(i).polarity.equals("neutral")) {
				if (result.get(i).equals("1.0")) {
					neu_to_pos.add(index);
				} else if (result.get(i).equals("-1.0")) {
					neu_to_neg.add(index);
				}
			}
		}
		
		
		System.out.println("pos_to_neu:" + pos_to_neu.size());
		ErrorStatistics.print_list(pos_to_neu);
		
		System.out.println("pos_to_neg:" + pos_to_neg.size());
		ErrorStatistics.print_list(pos_to_neg);
		
		System.out.println("neg_to_neu:" + neg_to_neu.size());
		ErrorStatistics.print_list(neg_to_neu);
		
		System.out.println("neg_to_pos:" + neg_to_pos.size());
		ErrorStatistics.print_list(neg_to_pos);
		
		System.out.println("neu_to_pos:" + neu_to_pos.size());
		ErrorStatistics.print_list(neu_to_pos);
		
		System.out.println("neu_to_neg:" + neu_to_neg.size());
		ErrorStatistics.print_list(neu_to_neg);
	}

}
