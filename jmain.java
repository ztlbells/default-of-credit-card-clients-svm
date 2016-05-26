package creditCard;
import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

import java.io.File;

import jxl.*;

import java.util.*;
//import creditCard.jInstance;

public class jmain {
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	
    	
        
    	training();
    	testing();
    }
        
    public static void training(){
    	//get data from data set. There are 30,000 instance in total. We take 80% of them as training data.
    	//i starts from 2
    	int i=2;
    	
        Sheet sheet;
    	Workbook book;
    	final int numInstance =10;
    	final int attr=23;
    	
    	//TODO normalization
    	//final int x1Bar=167484;
    	
    	svm_node data[][]=new svm_node[numInstance][attr];
    	double label[]=new double[numInstance];
    	
    	//definition of svm_parameter
    	svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.LINEAR;
        param.degree=3;
        param.cache_size = 100;
        param.eps = 0.00001;
        param.C = 1;

    	
    	try{
    	book= Workbook.getWorkbook(new File("D:/default of credit card clients.xls"));    	
    	sheet=book.getSheet(0); 
    	
    	while(i < numInstance+2){  
    		int j=1;
    		while(j <= attr){
    			//total dataset:dataSet, which saves all of the cell(map:cell->svm_node)    
    			System.out.println("i="+i+" j="+j);
    			data[i-2][j-1]=new svm_node();
    			
    			data[i-2][j-1].index=j-1;
    			data[i-2][j-1].value=Double.parseDouble(sheet.getCell(j,i).getContents());
    			   			
    			System.out.println("index="+data[i-2][j-1].index);    			   			
    			System.out.println("value="+data[i-2][j-1].value);
    			
    			j++;    		
        		}
    		label[i-2]=Double.parseDouble(sheet.getCell(24,i).getContents());
    		System.out.println("label="+label[i-2]);
    		i++;
    		}
    	
    	
    	}
    	catch(Exception e)  {e.printStackTrace(); } 
        
    	//svm_problem @param
        svm_problem problem = new svm_problem();
        problem.l = numInstance; 
        problem.x = data; 
        problem.y = label; 
        

        //if the parameters are correct, svm.svm_check_parameter() will return null.
        System.out.println(svm.svm_check_parameter(problem, param));          
        String modelFile="D:/svm_model.txt";        
        try{
        	//training
        	svm_model model = svm.svm_train(problem, param);
    		System.out.println("Done!!");
    		//save model file
    		svm.svm_save_model(modelFile, model);		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
   public static void testing(){
	   
	   Sheet sheet;
   		Workbook book;
   		final int numInstance =100;
   		final int attr=23;
   		svm_node [] test = new svm_node[attr];
   		
       try{
       	book= Workbook.getWorkbook(new File("D:/default of credit card clients.xls"));
       	sheet=book.getSheet(0); 
       	int j=1;
       	while(j <= attr){
   			
   			System.out.println("i="+24005+" j="+j);
   			test[j-1]=new svm_node();
   			test[j-1].index=j-1;
   			System.out.println("index="+test[j-1].index);
   			test[j-1].value=Double.parseDouble(sheet.getCell(j,24005).getContents());
   			
   			System.out.println("value="+test[j-1].value);
   			
   			j++;   		
       	}
       	}
       catch(Exception e)  { } 
       
       svm_model model;
       String modelFile="D:/svm_model.txt";
       try {
       model = svm.svm_load_model(modelFile);	//load model
       //testing
       System.out.println(svm.svm_predict(model, test));
       } 
       catch (Exception e) {e.printStackTrace();}
   }
    
}


