package sentidict;

import java.util.HashMap;
import java.util.Map;

import config.Config;
import edu.princeton.cs.introcs.In;

public class Afinn {
	
	public static Map<String,Integer> pos_map = new HashMap<String,Integer>();
	public static Map<String,Integer> neg_map = new HashMap<String,Integer>();
	
	/**
	 * ����AFINN��дʵ䡣ֵΪ���ļ���pos_map�У�ֵΪ���ļ���neg_map�У�ֵΪ0�Ĳ�����
	 */
	public static void init(){
		/*����ʹ�õ��Ǳ��˵�jar����stdlib-package.jar���Ժ��д�ļ���������������ȽϷ���*/
		In in = new In(Config.Afinn_input);
		System.out.println("------------������дʵ䣺AFINN-----------");
		while(in.hasNextLine()){
			String Line = in.readLine();
			String[] terms = Line.split("\t");
			if(terms.length==2){
				String word = terms[0].trim();			//ͨ��trim()������ȥ�������ײ���β���еĿո�
				int value = Integer.parseInt(terms[1]);
				if(value>0)
					pos_map.put(word, value);
				else if(value<0)
					neg_map.put(word, value);
				else{
					//���ﲻ�����Եĵ��ʿ��ǣ����Բ�����
				}
			}else{
				
				System.out.println("�ı��и�ʽ�����⣺"+Line);
			}
		}
		
		showDictInfo();
	}
	/**
	 * ��ʾ��дʵ�Ļ�����Ϣ
	 */
	public static void showDictInfo(){
		System.out.println(String.format("һ����%d�������ʻ�", pos_map.size()));
		System.out.println(String.format("һ����%d�������ʻ�", neg_map.size()));
	}
	public static void main(String[] args){
		Afinn.init();
	}
	
}
