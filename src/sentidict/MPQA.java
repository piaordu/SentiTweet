package sentidict;

import java.util.HashSet;
import java.util.Set;

import config.Config;
import edu.princeton.cs.introcs.In;

public class MPQA {
	public static Set<String> pos_set = new HashSet<String>();
	public static Set<String> neg_set = new HashSet<String>();
	
	public static void init(){
		In in = new In(Config.MPQA_input);
		System.out.println("-----����MPQA-----");
		while(in.hasNextLine()){
			String line = in.readLine();
			String[] terms = line.split(" ");
			if(terms.length == 6){
				String word_temp = terms[2].trim();
				String[] word = word_temp.split("=");
				String polarity = terms[5].trim();
				if(polarity.equals("priorpolarity=positive"))
					pos_set.add(word[1]);
				else if(polarity.equals("priorpolarity=negative"))
					neg_set.add(word[1]);
				else{
					
				}	
			}else{
				System.out.println("�ı���ʽ�����⣺" + line);
			}
		}
		
		showDictInfo();
	}
	
	public static void showDictInfo(){
		System.out.println(String.format("һ����%d�������ʻ�", pos_set.size()));
		System.out.println(String.format("һ����%d�������ʻ�", neg_set.size()));
	}
	
	public static void main(String[] args){
		MPQA.init();
	}
}
