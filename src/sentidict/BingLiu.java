package sentidict;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class BingLiu {
	public static Set<String> pos_set = new HashSet<String>();
	public static Set<String> neg_set = new HashSet<String>();
	
	public static void init(){
		In in_pos = new In(Config.BingLiu_Pos_input);
		System.out.println("-----加载情感词典：BingLiu_Positive-----");
		while(in_pos.hasNextLine()){
			String line = in_pos.readLine();
			if(line.startsWith(";") || line.trim().isEmpty())
				continue;
			String word_pos = line.trim();
			pos_set.add(word_pos);
			//System.out.println(word_pos);
			
		}
		in_pos.close();

		try {
			BufferedReader br = new BufferedReader(new FileReader(Config.BingLiu_Neg_input));
			System.out.println("-----加载情感词典：BingLiu_Negative-----");
			while(true){
				String line_neg = br.readLine();
				if(line_neg==null)
					break;
				if(line_neg.startsWith(";") || line_neg.trim().isEmpty())
					continue;
				String word_neg = line_neg.trim();
				neg_set.add(word_neg);
				//System.out.println(word_neg);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showDictInfo();
	}
	
	public static void showDictInfo(){
		System.out.println(String.format("一共有%d个积极词汇", pos_set.size()));
		System.out.println(String.format("一共有%d个消极词汇", neg_set.size()));
		
	}
	
	public static void main(String[] args){
		BingLiu.init();
	}
}
