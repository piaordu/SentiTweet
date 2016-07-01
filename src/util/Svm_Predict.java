package util;

import libsvm.*;
import java.io.*;
import java.util.*;

class Svm_Predict {
	private static svm_print_interface svm_print_null = new svm_print_interface()
	{
		public void print(String s) {}
	};

	private static svm_print_interface svm_print_stdout = new svm_print_interface()
	{
		public void print(String s)
		{
			System.out.print(s);
		}
	};

	private static svm_print_interface svm_print_string = svm_print_stdout;

	static void info(String s) {
		svm_print_string.print(s);
	}

	private static double atof(String s) {
		return Double.valueOf(s).doubleValue();
	}

	private static int atoi(String s) {
		return Integer.parseInt(s);
	}

	private static void predict(BufferedReader input, DataOutputStream output, svm_model model, int predict_probability) throws IOException {
		int correct = 0;
		
		int pos_correct = 0;//new TP检测到的正确的
		int neg_correct = 0;//new
		int neu_correct = 0;//new
		
		int pos_total = 0;//new TP+FP检测到的
		int neg_total = 0;//new
		int neu_total = 0;//new
		
		int pos_real = 0;//应该被检测到的
		int neg_real = 0;
		int neu_real = 0;
		
		int total = 0;
		double error = 0;
		double sumv = 0, sumy = 0, sumvv = 0, sumyy = 0, sumvy = 0;

		int svm_type=svm.svm_get_svm_type(model);
		int nr_class=svm.svm_get_nr_class(model);
		double[] prob_estimates=null;

		if (predict_probability == 1) {
			if(svm_type == svm_parameter.EPSILON_SVR ||
			   svm_type == svm_parameter.NU_SVR) {
				Svm_Predict.info("Prob. model for test data: target value = predicted value + z,\nz: Laplace distribution e^(-|z|/sigma)/(2sigma),sigma="+svm.svm_get_svr_probability(model)+"\n");
			} else {
				int[] labels=new int[nr_class];
				svm.svm_get_labels(model,labels);
				prob_estimates = new double[nr_class];
				output.writeBytes("labels");
				for(int j=0;j<nr_class;j++)
					output.writeBytes(" "+labels[j]);
				output.writeBytes("\n");
			}
		}
		
		while (true) {
			String line = input.readLine();
			if(line == null) break;

			StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");

			double target = atof(st.nextToken());
			
			int m = st.countTokens()/2;
			svm_node[] x = new svm_node[m];
			for (int j=0;j<m;j++) {
				x[j] = new svm_node();
				x[j].index = atoi(st.nextToken());
				x[j].value = atof(st.nextToken());
			}

			double v;
			
			if (predict_probability==1 && (svm_type==svm_parameter.C_SVC || svm_type==svm_parameter.NU_SVC))
			{
				v = svm.svm_predict_probability(model,x,prob_estimates);
				output.writeBytes(v+" ");
				for(int j=0;j<nr_class;j++)
					output.writeBytes(prob_estimates[j]+" ");
				output.writeBytes("\n");
			} else {
				v = svm.svm_predict(model,x);
				output.writeBytes(v+"\n");
			}

			if(v == target)
				++correct;
			
			if (v == 1) {
				pos_total ++;
				if (v == target)
					pos_correct ++;
			} else if (v == -1) {
				neg_total ++;
				if (v == target)
					neg_correct ++;
			} else if (v == 0) {
				neu_total ++;
				if (v == target)
					neu_correct ++;
			}
			
			if (target == 1)
				pos_real ++;
			else if (target == -1)
				neg_real ++;
			else if (target == 0)
				neu_real ++;
			
			error += (v-target)*(v-target);
			sumv += v;
			sumy += target;
			sumvv += v*v;
			sumyy += target*target;
			sumvy += v*target;
			++total;
		}
		
		if(svm_type == svm_parameter.EPSILON_SVR ||
		   svm_type == svm_parameter.NU_SVR) {
			Svm_Predict.info("Mean squared error = "+error/total+" (regression)\n");
			Svm_Predict.info("Squared correlation coefficient = "+
				 ((total*sumvy-sumv*sumy)*(total*sumvy-sumv*sumy))/
				 ((total*sumvv-sumv*sumv)*(total*sumyy-sumy*sumy))+
				 " (regression)\n");
		} else {
			Svm_Predict.info("Accuracy = "+(double)correct/total*100+
				 "% ("+correct+"/"+total+") (classification)\n");
			
			double pos_p = (double)pos_correct / pos_total;
			double neg_p = (double)neg_correct / neg_total;
			double neu_p = (double)neu_correct / neu_total;
			
			double pos_r = (double)pos_correct/ pos_real;
			double neg_r = (double)neg_correct/ neg_real;
			double neu_r = (double)neu_correct/ neu_real;
			
			double pos_f = 2 * pos_p * pos_r / (pos_p + pos_r);
			double neg_f = 2 * neg_p * neg_r / (neg_p + neg_r);
			double neu_f = 2 * neu_p * neu_r / (neu_p + neu_r);
			
			double a_f = (pos_f + neg_f + neu_f) / 3;
			
			Svm_Predict.info("Postive F = " + pos_f + " " + "pos_r = " + pos_r + " " + "pos_p = " + pos_p + "\n");
			Svm_Predict.info("Negative F = " + neg_f + " " + "neg_r = " + neg_r + " " + "neg_p = " + neg_p + "\n");
			Svm_Predict.info("Neutral F = " + neu_f + " " + "neu_r = " + neu_r + " " + "neu_p = " + neu_p + "\n");
			Svm_Predict.info("Average F1 = " + a_f);
		}
	}

	private static void exit_with_help()
	{
		System.err.print("usage: svm_predict [options] test_file model_file output_file\n"
		+"options:\n"
		+"-b probability_estimates: whether to predict probability estimates, 0 or 1 (default 0); one-class SVM not supported yet\n"
		+"-q : quiet mode (no outputs)\n");
		System.exit(1);
	}

	public static void main(String argv[]) throws IOException
	{
		int i, predict_probability=0;
        	svm_print_string = svm_print_stdout;

		// parse options
		for(i=0;i<argv.length;i++)
		{
			if(argv[i].charAt(0) != '-') break;
			++i;
			switch(argv[i-1].charAt(1))
			{
				case 'b':
					predict_probability = atoi(argv[i]);
					break;
				case 'q':
					svm_print_string = svm_print_null;
					i--;
					break;
				default:
					System.err.print("Unknown option: " + argv[i-1] + "\n");
					exit_with_help();
			}
		}
		if(i>=argv.length-2)
			exit_with_help();
		try 
		{
			BufferedReader input = new BufferedReader(new FileReader(argv[i]));
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(argv[i+2])));
			svm_model model = svm.svm_load_model(argv[i+1]);
			if (model == null)
			{
				System.err.print("can't open model file "+argv[i+1]+"\n");
				System.exit(1);
			}
			if(predict_probability == 1)
			{
				if(svm.svm_check_probability_model(model)==0)
				{
					System.err.print("Model does not support probabiliy estimates\n");
					System.exit(1);
				}
			}
			else
			{
				if(svm.svm_check_probability_model(model)!=0)
				{
					Svm_Predict.info("Model supports probability estimates, but disabled in prediction.\n");
				}
			}
			predict(input,output,model,predict_probability);
			input.close();
			output.close();
		} 
		catch(FileNotFoundException e) 
		{
			exit_with_help();
		}
		catch(ArrayIndexOutOfBoundsException e) 
		{
			exit_with_help();
		}
	}
}
