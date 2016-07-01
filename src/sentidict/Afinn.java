package sentidict;

import java.util.HashMap;
import java.util.Map;

import config.Config;
import edu.princeton.cs.introcs.In;

public class Afinn {
	
	public static Map<String,Integer> pos_map = new HashMap<String,Integer>();
	public static Map<String,Integer> neg_map = new HashMap<String,Integer>();
	
	/**
	 * 加载AFINN情感词典。值为正的加入pos_map中，值为负的加入neg_map中，值为0的不处理。
	 */
	public static void init(){
		/*这里使用的是别人的jar包：stdlib-package.jar，以后读写文件都可以用这个，比较方便*/
		In in = new In(Config.Afinn_input);
		System.out.println("------------加载情感词典：AFINN-----------");
		while(in.hasNextLine()){
			String Line = in.readLine();
			String[] terms = Line.split("\t");
			if(terms.length==2){
				String word = terms[0].trim();			//通过trim()函数，去掉单词首部和尾部中的空格
				int value = Integer.parseInt(terms[1]);
				if(value>0)
					pos_map.put(word, value);
				else if(value<0)
					neg_map.put(word, value);
				else{
					//这里不对中性的单词考虑，所以不处理
				}
			}else{
				
				System.out.println("文本行格式有问题："+Line);
			}
		}
		
		showDictInfo();
	}
	/**
	 * 显示情感词典的基本信息
	 */
	public static void showDictInfo(){
		System.out.println(String.format("一共有%d个积极词汇", pos_map.size()));
		System.out.println(String.format("一共有%d个消极词汇", neg_map.size()));
	}
	public static void main(String[] args){
		Afinn.init();
	}
	
}
