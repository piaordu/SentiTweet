package util;
import java.io.IOException;

import config.Config;
public class Test {
     /*iter ��������
      * epsilon ���ι滮����ֹ����
      * obj ���ι滮����Сֵ  ��żSVM��������Ŀ��ֵ
      * rho �о������ĳ�����   ���ߺ�����ƫ����
      * nSV ֧�������ĸ���  
      * nBSV  �н�֧�������ĸ���
      * nu	nu-svm��Ӧ�Ĳ���
      */
	public static void main(String[] args) {
		
		// SVMѵ��ģ���õ���������·��   //SVMͨ��ѵ������ѵ��������ģ�͵�·��
		String[] arg = { Config.Feature_Train_old, Config.Svm_Model_old };
		//���Ե����������ļ�   //SVMͨ��ѵ������ѵ��������ģ���ļ�   //���ɵĽ���ļ�
        String[] parg = { Config.Feature_Test_old, Config.Svm_Model_old, Config.Svm_Out_old }; 
       
        System.out.println("........SVM���п�ʼ..........");
        
        /*		����ֱ�ӵ���SVM�Դ���ѵ����Ͳ�����		*/
        try {
        	Svm_Train.main(arg);
        	Svm_Predict.main(parg); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
