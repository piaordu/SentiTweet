package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Tweet;
import sentidict.Afinn;
import sentidict.BingLiu;
import sentidict.MPQA;
import sentidict.Negation;
import sentidict.Sentiment;
import config.Config;
import discourseRelations.DiscourseFeature;
import discourseRelations.DiscourseFeatureNew;
import edu.princeton.cs.introcs.Out;

public class Feature {
	static{
		BingLiu.init();
		Afinn.init();
		MPQA.init();
		Negation.init();
	}
	/**
	 * count numbers of polarity words
	 * @param tweet
	 * @return
	 */
	public static List<Double> countToks(Tweet tweet){
		List<Double> toks_num = new ArrayList<Double>();
		double pos_num = 0.0;
		double neg_num = 0.0;
		List<String> toks = tweet.tokenList;
		for(int i = 0; i < toks.size(); i++){
			if(Sentiment.tokenPolarityList.get(i)==1){
				pos_num +=1;
			}else if(Sentiment.tokenPolarityList.get(i)==-1){
				neg_num += 1;
			}
		}
		toks_num.add(pos_num);
		toks_num.add(neg_num);
		return toks_num;
	}
	
	/**
	 * 第一个情感词（积极/消极）的位置索引
	 * @param tweet
	 * @return
	 */
	public static List<Double> findFirstPolarityIndex(Tweet tweet){
		List<Double> list = new ArrayList<Double>();
		double pos_firstIndex = -1;
		double neg_firstIndex = -1;
		List<String> toks = tweet.tokenList;
		for(int i = 0; i < toks.size(); i++){
			if(Sentiment.tokenPolarityList.get(i)==1){
				if(pos_firstIndex == -1)
					pos_firstIndex = i;
			}
			if(Sentiment.tokenPolarityList.get(i) == -1){
				if(neg_firstIndex == -1)
					neg_firstIndex = i;
			}
		}
		list.add(pos_firstIndex);
		list.add(neg_firstIndex);
		return list;
	}
	
	/**
	 * 特征：twitter文本里是否有否定词
	 * @param tweet
	 * @return
	 */
	public static double hasNegation(Tweet tweet){
		List<String> tokens = tweet.tokenList;
		for(String token:tokens){
			if(Negation.negationSet.contains(token))
				return 1.0;
		}
		return 0.0;
	}
	
	/**
	 * 情感词前面时候否否定词
	 * @param tweet
	 * @return
	 */
	public static List<Double> hasNegationBeforePolarity(Tweet tweet){
		double hasNegationBeforePos = 0.0;
		double hasNegationBeforeNeg = 0.0;
		double negationIndex = -1;
		List<String> toks = tweet.tokenList;
		
		for(int i = 0; i < toks.size(); i++){
			if(Sentiment.negationList.get(i)){
				negationIndex = i;
			}
			if(Sentiment.tokenPolarityList.get(i) == 1){
				if(i > negationIndex)
					hasNegationBeforePos=1;
			}
			if(Sentiment.tokenPolarityList.get(i) == -1){
				if(i > negationIndex)
					hasNegationBeforeNeg = 1;
			}
		}
		
		List<Double> list = new ArrayList<Double>();
		list.add(hasNegationBeforePos);
		list.add(hasNegationBeforeNeg);
		return list;
	}
	
	/**
	 * 词袋，每个token出现的次数
	 * @param tweet
	 * @return
	 */
	public static List<Double> tweet_vector(Tweet tweet) {
		int size = BagOfWords.dict.size();
		List<String> tokenList = tweet.getTokenList();
		List<Double> result = new ArrayList<Double>();
		
		/*
		//start new-discourse-relations.1
		double[] discourse_relation_new = DiscourseFeatureNew.calcu_discourse_relation(tokenList);
		double[] discourse_result = new double[size];
		Map<String, Integer> word_weight = new HashMap<String, Integer>();
		int j = 0;
		for (String token:tokenList) {
			if (word_weight.containsKey(token)) {
				word_weight.put(token, word_weight.get(token) + (int)discourse_relation_new[j]);
			} else {
				word_weight.put(token, (int)discourse_relation_new[j]);
			}
			j += 1;
		}
		
		for(String key:word_weight.keySet()) {
			if (BagOfWords.dict.contains(key)) {
				int index_key = BagOfWords.dict.indexOf(key);
				discourse_result[index_key] = word_weight.get(key);
			}
		}
		
		for (int i = 0; i < size; i ++) {
			result.add(discourse_result[i]);
		}
		
		return result;
		//end new-discourse-relations.
		*/
		
		/*
		//start old-discourse-relations.2
		double[] discourse_relation = DiscourseFeature.discourse_relations(tokenList);
		double[] discourse_result = new double[size];
		Map<String, Integer> word_weight = new HashMap<String, Integer>();
		int j = 0;
		for (String token:tokenList) {
			if (word_weight.containsKey(token)) {
				word_weight.put(token, word_weight.get(token) + (int)discourse_relation[j]);
			} else {
				word_weight.put(token, (int)discourse_relation[j]);
			}
			j += 1;
		}
		
		for(String key:word_weight.keySet()) {
			if (BagOfWords.dict.contains(key)) {
				int index_key = BagOfWords.dict.indexOf(key);
				discourse_result[index_key] = word_weight.get(key);
			}
		}
		
		for (int i = 0; i < size; i ++) {
			result.add(discourse_result[i]);
		}
		
		return result;
		
		//end old-discourse-relations.
		*/
		
		//start without discourse.3
		double[] word_freq = new double[size];
		for (String token:tokenList) {
			if(BagOfWords.dict.contains(token)) {
				int index = BagOfWords.dict.indexOf(token);
				word_freq[index] += 1.0;
			}
		}
		
		for (int i = 0; i < size; i++) {
			result.add(word_freq[i]);
		}
		
		return result;
		//end without discourse.
		
		/*
		double[][] discourse_relation = DiscourseFeature.discourse_relations(tokenList);
		
		for (String token:tokenList) {
			if(BagOfWords.dict.contains(token)) {
				int index = BagOfWords.dict.indexOf(token);
				word_freq[index] += 1.0;
			}
		}
		
		int l = 0;
		for (int i = 0; i < word_freq.length; i ++) {
			result.add(word_freq[i]);
			if (word_freq[i] > 0) {
				result.add(discourse_relation[l][0]);
				result.add(discourse_relation[l][1]);
				result.add(discourse_relation[l][2]);
				l ++;
			} else {
				result.add(0.0);
				result.add(0.0);
				result.add(0.0);
			}
		}	
		
		return result;
		*/
	}
	
	public static List<Double> featureExtract(Tweet tweet){
		Sentiment.init(tweet.tokenList);
		List<Double> featureList = new ArrayList<Double>();
		
		featureList.addAll(tweet_vector(tweet));
		featureList.addAll(findFirstPolarityIndex(tweet));
		featureList.addAll(countToks(tweet));
		featureList.add(hasNegation(tweet));
		featureList.addAll(hasNegationBeforePolarity(tweet));
		return featureList;
	}
	
	/**
	 * 计算归一化的参数
	 * @param tweetList
	 * @return
	 */
	public static double[][] normalize(List<Tweet> tweetList) {
		int size = BagOfWords.dict.size() + 7;
		double[] max = new double[size];
		double[] min = new double[size];
		double[][] norm_arg = new double[size][2];
		
		for (Tweet tweet:tweetList) {
			List<Double> featureList = Feature.featureExtract(tweet);
			for (int i = 0; i < size; i ++) {
				if (max[i] < featureList.get(i)) 
					max[i] = featureList.get(i);
				if (min[i] > featureList.get(i))
					min[i] = featureList.get(i);	
			}
		}
		
		for (int i = 0; i < size; i ++) {
			norm_arg[i][0] = min[i];
			norm_arg[i][1] = max[i] - min[i];
		}
		
		System.out.println("Get norm arguments...");
		return norm_arg;
	}
	
	public static void main(String[] args){
		//最后将featureList写到文件里，一行
		Out out = new Out(Config.Feature_Train);
		List<Tweet> tweetList = PreProcess.loadTrainCorpus();
		
		double[][] norm = Feature.normalize(tweetList);	
		
		for(Tweet tweet:tweetList){
			List<Double> featureList = Feature.featureExtract(tweet);
			//System.out.println(featureList);
			String polarity = tweet.getPolarity();
			if (polarity.equals("positive")) {
				out.print(1 + " ");
			} else if (polarity.equals("negative")) {
				out.print(-1 + " ");
			} else {
				out.print(0 + " ");
			}
			
			for (int i = 0; i < featureList.size(); i ++) {
				double temp = featureList.get(i);				
				if (norm[i][1] == 0) {
					//out.print(i + ":" + temp + " ");
					continue;
				}
				temp = (temp - norm[i][0])/norm[i][1];
				if (temp != 0) {
					out.print(i + ":" + temp + " ");
				}
			}
			out.println();
		}
		System.out.println("******feature of train data extracted!******");
		
		
		Out out_test = new Out(Config.Feature_Test);
		List<Tweet> tweetList_test = PreProcess.loadTestCorpus();
		double[][] norm_test = Feature.normalize(tweetList_test);
		for(Tweet tweet:tweetList_test){
			List<Double> featureList = Feature.featureExtract(tweet);
			String polarity = tweet.getPolarity();
			if (polarity.equals("positive")) {
				out_test.print(1 + " ");
			} else if (polarity.equals("negative")) {
				out_test.print(-1 + " ");
			} else {
				out_test.print(0 + " ");
			}
			for (int i = 0; i < featureList.size(); i ++) {
				double temp = featureList.get(i);
				if (norm_test[i][1] == 0) {
					//out_test.print(i + ":" + temp + " ");
					continue;
				}
				temp = (temp - norm_test[i][0])/norm_test[i][1];				
				if (temp != 0) {
					out_test.print(i + ":" + temp + " ");
				}
			}
			out_test.println();
		}
		
		System.out.println("*****feature of test data extracted!*****");
	}
}