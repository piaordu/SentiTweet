package util;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import model.Tweet;
import cmu.arktweetnlp.Twokenize;
import edu.princeton.cs.introcs.In;

public class PreProcess {
	/**
	 * ����tweetѵ������
	 * @return
	 */
	public static List<Tweet> loadTrainCorpus(){
		List<Tweet> tweetList = new ArrayList<Tweet>();
		System.out.println("------------����tweetѵ������-----------");
		In in = new In(Config.train_input);
		while(in.hasNextLine()){
			String line = in.readLine();
			String[] terms = line.split("\t");
			if(terms.length == 4){
				String polarity = terms[2].trim();
				//System.out.println(polarity);
				String text = terms[3];
				//System.out.println(text);
				tweetList.add(new Tweet(text,polarity));
			}else{
				System.out.println("���ݸ�ʽ����ȷ");
			}
		}
		
		for(Tweet tweet:tweetList){
			Tokenization(tweet);
		}
		
		return tweetList;
	}
	/**
	 * ����tweet��������
	 * @return
	 */
	public static List<Tweet> loadTestCorpus(){
		List<Tweet> tweetList = new ArrayList<Tweet>();
		System.out.println("------------����tweet��������-----------");
		In in = new In(Config.test_input);
		while(in.hasNextLine()){
			String line = in.readLine();
			String[] terms = line.split("\t");
			if(terms.length == 4){
				String polarity = terms[2].trim();
				String text = terms[3];
				//System.out.println(text);
				tweetList.add(new Tweet(text,polarity));
			}
		}
		
		for(Tweet tweet:tweetList){
			Tokenization(tweet);
			
		}
		
		return tweetList;
	}
	
	/**
	 * �������ark-tweet-nlp-0.3.2.jar���߰���Twokenize����зִʣ�Ч�����ޣ�
	 * @param tweet
	 */
	public static void Tokenization(Tweet tweet){
		List<String> toks = Twokenize.tokenizeRawTweetText(tweet.text);
		/*
		for (int i=0; i<toks.size(); i++) {
			System.out.println(toks.get(i));
		}
		System.out.println();
		*/
		tweet.setTokenList(toks);
		//System.out.println(toks);
	}
	
	public static void main(String[] args){
		/*		���Էִ�Ч��		*/
		Tweet tweet = new Tweet("Special THANKS to EVERYONE for coming out to Taboo Tuesday With DST tonight! It was FUN&amp;educational!!! :) @XiEtaDST","positive");
		Tokenization(tweet);
		
		loadTrainCorpus();
		loadTestCorpus();
	}
}
