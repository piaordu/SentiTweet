package discourseRelations;

import java.util.List;

import sentidict.Afinn;
import sentidict.BingLiu;
import sentidict.MPQA;
import sentidict.Negation;

public class DiscourseFeatureNew {
	final static int begin_window = 5;
	final static int neg_window = 5;
	
	static{
		ConjFol.init();
		ConjPrev.init();
		ConjInfer.init();
		Conditionals.init();
		StrongMod.init();
		WeakMod.init();
		Neg.init();
		
		SentenceInitial.initial();
		MidFol.initial();
		MidPrev.initial();
	}
	
	static{
		BingLiu.init();
		Afinn.init();
		MPQA.init();
		Negation.init();
	}
	
	public static double[] calcu_discourse_relation(List<String> tokens){
		double[] f = new double[tokens.size()];
		double[] flip = new double[tokens.size()];
		double[] hyp = new double[tokens.size()];
		
		//double[][] word_result = new double[tokens.size()][3];
		
		if (SentenceInitial.SentenceInitial_set.contains(tokens.get(0))) {
			for (int j = begin_window; j < tokens.size(); j++) {
				f[j] += 1;
			}
		}
		
		for (int i = 0; i < tokens.size(); i ++) {
			f[i] = 1;
			hyp[i] = 0;
			if (Conditionals.Conditionals_set.contains(tokens.get(i)) || StrongMod.StrongMod_set.contains(tokens.get(i)))
				hyp[i] = 1;
		}
		
		for (int i = 0; i < tokens.size(); i ++) {
			flip [i] = 1;
			if (ConjFol.ConjFol_set.contains(tokens.get(i)) || ConjInfer.ConjInfer_set.contains(tokens.get(i)) || MidFol.MidFol_set.contains(tokens.get(i))) {
				//System.out.print("ConjFol_contains");
				for (int j = i + 1; j < tokens.size(); j ++) {
					f[j] += 1;
				}
			} else if (ConjPrev.ConjPrev_set.contains(tokens.get(i)) || MidPrev.MidPrev_set.contains(tokens.get(i))) {
				//System.out.print("ConjPrev_contains");
				for (int j = 0; j < i - 1; j ++) {
					f[j] += 1;
				}
			} else if (Neg.Neg_set.contains(tokens.get(i))) {
				//System.out.print("Neg_contains");
				for (int j = 1; j < neg_window; j ++) {
					if (!(ConjPrev.ConjPrev_set.contains(tokens.get(j)) || ConjFol.ConjFol_set.contains(tokens.get(j)) || MidPrev.MidPrev_set.contains(tokens.get(i)) || MidFol.MidFol_set.contains(tokens.get(i)))) {
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
		
		//return word_result;这里返回的是三维的数组double[][]
		
		return f;//double[]只返回数组f
	}
}
