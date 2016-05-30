package creditCard;

import java.io.IOException;

public class CompressData {
	private String[] argv;
	public void normalize(){
		
		svm_scale s = new svm_scale();
		
		try {
			s.run(this.argv);
		} catch (IOException e) {e.printStackTrace();}
		//System.out.println("compression done.");
	}
	public void setArgv(String[] givenArgv){
		this.argv=givenArgv;
	}
}
