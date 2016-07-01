package config;

import java.io.File;

public class Config {
	
	public static String train_input = "Corpus/Train_B.tsv";
	public static String test_input = "Corpus/Test_B.tsv";
	
	public static String Afinn_input = "SentiDict/AFINN/AFINN.txt";
	public static String BingLiu_Pos_input = "SentiDict/BingLiu/pos.txt";
	public static String BingLiu_Neg_input = "SentiDict/BingLiu/neg.txt";
	public static String MPQA_input = "SentiDict/MPQA/MPQA.tff";
	
	public static String Negation_input = "SentiDict/Negation/negation.txt";
	public static String SentiWordNet_input="SentiDict/SentiWordNet/SentiWordNet_3.0.0.txt";
	
	//The result without discourse relations.
	public static String Feature_Train = "Feature/Train.txt";
	public static String Feature_Test = "Feature/Test.txt";
	
	//The results of old-discourse-relations.
	public static String Feature_Train_old = "Feature/Train_old.txt";
	public static String Feature_Test_old = "Feature/Test_old.txt";
	
	//The results of new-discourse-relations.
	public static String Feature_Train_new = "Feature/Train_new.txt";
	public static String Feature_Test_new = "Feature/Test_new.txt";
	
	//The result without discourse relations.
	public static String Svm_Model_Path = "SVM/model.txt";
	public static String Svm_Out_Path = "SVM/out.txt";
	
	//The svm model of old-discourse-relations.
	public static String Svm_Model_old = "SVM/model_old.txt";
	public static String Svm_Out_old = "SVM/out_old.txt";
	
	//The svm model of new-discourse-relations.
	public static String Svm_Model_new = "SVM/model_new.txt";
	public static String Svm_Out_new = "SVM/out_new.txt";
	
	public static String ConjFol_input = "DiscourseRelations/ConjFol.txt";
	public static String ConjPrev_input = "DiscourseRelations/ConjPrev.txt";
	public static String ConjInfer_input = "DiscourseRelations/ConjInfer.txt";
	public static String Conditionals_input = "DiscourseRelations/Conditionals.txt";
	public static String StrongMod_input = "DiscourseRelations/StrongMod.txt";
	public static String WeakMod_input = "DiscourseRelations/WeakMod.txt";
	public static String Neg_input = "DiscourseRelations/Neg.txt";
	public static String SentenceInitial_input = "DiscourseRelations/SentenceInitial.txt";
	public static String MidFol_input = "DiscourseRelations/MidFol.txt";
	public static String MidPrev_input = "DiscourseRelations/MidPrev.txt";
	
	public static void main(String[] args){
		File file = new File("Corpus/Test_A.tsv");
		System.out.println(file.getAbsolutePath());
	}
}
