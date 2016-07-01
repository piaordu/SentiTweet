package util;
import java.io.IOException;

import config.Config;
public class Test {
     /*iter 迭代次数
      * epsilon 二次规划的终止条件
      * obj 二次规划的最小值  对偶SVM问题的最佳目标值
      * rho 判决函数的常数向   决策函数的偏置项
      * nSV 支持向量的个数  
      * nBSV  有界支持向量的个数
      * nu	nu-svm对应的参数
      */
	public static void main(String[] args) {
		
		// SVM训练模型用的特征数据路径   //SVM通过训练数据训练出来的模型的路径
		String[] arg = { Config.Feature_Train_old, Config.Svm_Model_old };
		//测试的特征数据文件   //SVM通过训练数据训练出来的模型文件   //生成的结果文件
        String[] parg = { Config.Feature_Test_old, Config.Svm_Model_old, Config.Svm_Out_old }; 
       
        System.out.println("........SVM运行开始..........");
        
        /*		这里直接调用SVM自带的训练类和测试类		*/
        try {
        	Svm_Train.main(arg);
        	Svm_Predict.main(parg); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
