/**
 * 
 */
package creditCard;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import jxl.*;
/**
 * @author ztlbells
 *
 */
public class DataSet {
	private String restorePath;
	private String savePath;
	
	private int size;
	private int attributes;
	
	private int row;
	private int col;
	
	//constructor
	 DataSet(String restorePath, String savePath,int size,int attr){
		this.restorePath=restorePath;
		this.savePath=savePath;
		this.size=size;
		this.attributes=attr;
	}
	 
	public void setStart(int startRow, int startCol){
		//as we do not know the format of specific .xls, the starting row number should be given.
		//so as the column number. ( starts from 0)
		this.row=startRow;
		this.col=startCol;
	}
	
	
	//execute function
	public void run(){
		
		Sheet sheet;
	    Workbook book;
	    BufferedWriter fw = null;
	    
	    try{
	    	//open .xls file : original data set file
	    	book= Workbook.getWorkbook(new File(this.restorePath));    	
	    	sheet=book.getSheet(0); 
	    	
	    	//initialize iterator
	    	int i=this.row;
	    	
	    	//target data set file
	    	File file = new File(this.savePath);
    		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); 
    		
	    	try{
	    		//open txt file
	    		while(i < this.row+this.size ){
	    			
	    			/**
	    			 * output format for libsvm:
	    			 * [label] <index1>:[value] <index2>:[value] <index3>:[value] ..
	    			 * [label] <index1>:[value] <index2>:[value] <index3>:[value] ..
	    			 */
	    			double temp=Double.parseDouble(sheet.getCell(24,i).getContents());
	    			if (temp == 0.0){
	    				System.out.println("i="+i+",0->-1");
	    				fw.write("-1");
	    			}
	    			else if(temp == 1.0){
	    				System.out.println("i="+i+",1");
	    				fw.write(sheet.getCell(24,i).getContents());
	    			}
	    			
	    			int j=this.col;
	    			while(j <= this.attributes){
	    				
	    				//System.out.println("i="+i+" j="+j);
	    				//System.out.println(sheet.getCell(j,i).getContents());
	    				
	    				fw.write(" "+j + ":" + sheet.getCell(j,i).getContents());
	    				j++;
	    			}
	    			fw.write("\n");
	    			i++;	    			
	    		}	
	    	}
	    	catch(Exception e){e.printStackTrace();}
	    	if (fw != null) {
	    	//close txt file
				try {
					fw.close();
				} catch (IOException e) {e.printStackTrace();}
	    	
	    	}
	    }
	    	catch(Exception e)  {e.printStackTrace(); }
	}
}
