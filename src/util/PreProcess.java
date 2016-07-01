package util;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import model.Tweet;
import cmu.arktweetnlp.Twokenize;
import edu.princeton.cs.introcs.In;

public class PreProcess {
	/**
	 * 加载tweet训练数据
	 * @return
	 */
	public static List<Tweet> loadTrainCorpus(){
		List<Tweet> tweetList = new ArrayList<Tweet>();
		System.out.println("------------加载tweet训练数据-----------");
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
				System.out.println("数据格式不正确");
			}
		}
		
		for(Tweet tweet:tweetList){
			Tokenization(tweet);
		}
		
		return tweetList;
	}
	/**
	 * 加载tweet测试数据
	 * @return
	 */
	public static List<Tweet> loadTestCorpus(){
		List<Tweet> tweetList = new ArrayList<Tweet>();
		System.out.println("------------加载tweet测试数据-----------");
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
	 * 这里调用ark-tweet-nlp-0.3.2.jar工具包的Twokenize类进行分词，效果很赞！
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
		/*		测试分词效果		*/
		Tweet tweet = new Tweet("Special THANKS to EVERYONE for coming out to Taboo Tuesday With DST tonight! It was FUN&amp;educational!!! :) @XiEtaDST","positive");
		Tokenization(tweet);
		
		loadTrainCorpus();
		loadTestCorpus();
	}
}
