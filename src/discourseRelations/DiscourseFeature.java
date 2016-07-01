package discourseRelations;

import java.util.List;

import sentidict.Afinn;
import sentidict.BingLiu;
import sentidict.MPQA;
import sentidict.Negation;

public class DiscourseFeature {
	static {
		ConjFol.init();
		ConjPrev.init();
		ConjInfer.init();
		Conditionals.init();
		StrongMod.init();
		WeakMod.init();
		Neg.init();
	}
	
	static{
		BingLiu.init();
		Afinn.init();
		MPQA.init();
		Negation.init();
	}
	
	final static int Neg_Window = 5;
	
	/*
	public static int find_Conditionals_StrongMod(String token) {
		if (Conditionals.Conditionals_set.contains(token) || StrongMod.StrongMod_set.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}
	*/
	
	/* page 1856 Equation 1
	public static double[] polarity_weight(List<String> tokens) {
		int hyp = 0;
		double[] pol = new double[tokens.size()];
		double[] f = new double[tokens.size()];
		//System.out.println(tokens);
		
		for (int i = 0; i < tokens.size(); i ++) {
			if (Conditionals.Conditionals_set.contains(tokens.get(i)) || StrongMod.StrongMod_set.contains(tokens.get(i))) {
				hyp = 1;
			}
		}
		
		for (int i = 0; i < tokens.size(); i ++) {
			f[i] = 1;
		}
		
		for (int i = 0; i < tokens.size(); i ++) {
			if (Afinn.pos_map.containsKey(tokens.get(i)) || BingLiu.pos_set.contains(tokens.get(i)) || MPQA.pos_set.contains(tokens.get(i))) {
				//System.out.print("pos_contains");
				pol[i] += 1;
			} else if (Afinn.neg_map.containsKey(tokens.get(i)) || BingLiu.neg_set.contains(tokens.get(i)) || MPQA.neg_set.contains(tokens.get(i))) {
				//System.out.print("neg_contains");
				pol[i] += -1;
			} else {
				pol[i] += 0;
			}
			
			if (hyp == 1)
				pol[i] *= 0.5;
			
			//System.out.print(pol[i] + " ");
		}
		
		return pol;
	}
	*/
	
	public static double[] discourse_relations(List<String> tokens) {
		double[] f = new double[tokens.size()];	
		double[] flip = new double[tokens.size()];
		double[] hyp = new double[tokens.size()];
		
		//double[][] word_result = new double[tokens.size()][3];
		//double[] polarity = polarity_weight(tokens);
		
		for (int i = 0; i < tokens.size(); i ++) {
			f[i] = 1;
			hyp[i] = 0;
			if (Conditionals.Conditionals_set.contains(tokens.get(i)) || StrongMod.StrongMod_set.contains(tokens.get(i)))
				hyp[i] = 1;
		}
		
		for (int i = 0; i < tokens.size(); i ++) {
			flip [i] = 1;
			if (ConjFol.ConjFol_set.contains(tokens.get(i)) || ConjInfer.ConjInfer_set.contains(tokens.get(i))) {
				//System.out.print("ConjFol_contains");
				for (int j = i + 1; j < tokens.size(); j ++) {
					f[j] += 1;
				}
			} else if (ConjPrev.ConjPrev_set.contains(tokens.get(i))) {
				//System.out.print("ConjPrev_contains");
				for (int j = 0; j < i - 1; j ++) {
					f[j] += 1;
				}
			} else if (Neg.Neg_set.contains(tokens.get(i))) {
				//System.out.print("Neg_contains");
				for (int j = 1; j < Neg_Window; j ++) {
					if (!(ConjPrev.ConjPrev_set.contains(tokens.get(j)) || ConjFol.ConjFol_set.contains(tokens.get(j)))) {
						if (i + j < tokens.size())
							flip[i+j] = -1;
					}
				}
			}
			
			//polarity[i] = f[i] * flip[i] * polarity[i];
			
			//System.out.print(flip[i]);
			//System.out.print(f[i]);
			//System.out.print(polarity[i]);
		}
		
		/*
		for (int i = 0; i < tokens.size(); i ++) {
			word_result[i][0] = f[i];
			word_result[i][1] = flip[i];
			word_result[i][2] = hyp[i];
		}
		*/
		
		//return word_result;
		
		return f;
	}
	
	/*
	public static void main(String[] args) {
		List<Tweet> tweetList = PreProcess.loadTrainCorpus();
		for (Tweet tweet:tweetList) {
			double[] polarity = multiply_polarity(tweet.getTokenList());
			for (int i = 0; i <polarity.length; i ++) {
				System.out.print(polarity[i] + " ");
			}
			System.out.println();
		}
		
	}
	*/
}
