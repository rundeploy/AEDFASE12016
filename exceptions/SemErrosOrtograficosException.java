package exceptions;
/**
 *@author CRISTIAN MITUL
 */

public class SemErrosOrtograficosException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public SemErrosOrtograficosException(){
		super();
	}
	
	public SemErrosOrtograficosException(String message){
		super(message);
	}
}
