package sentidict;

import java.util.ArrayList;
import java.util.List;

public class Sentiment {
	public static List<Integer> tokenPolarityList;
	public static List<Boolean> negationList;
	
	
	public static void init(List<String> tokenList){
		tokenPolarityList = new ArrayList<Integer>();
		negationList = new ArrayList<Boolean>();
		
		for(String token : tokenList){
			if(Afinn.pos_map.containsKey(token) || BingLiu.pos_set.contains(token) || MPQA.pos_set.contains(token))
				tokenPolarityList.add(1);
			else if(Afinn.neg_map.containsKey(token) || BingLiu.neg_set.contains(token) || MPQA.neg_set.contains(token))
				tokenPolarityList.add(-1);
			else
				tokenPolarityList.add(0);
			
			if(Negation.negationSet.contains(token)){
				negationList.add(true);
			}else{
				negationList.add(false);
			}
		}
	}
}
