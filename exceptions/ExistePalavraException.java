package exceptions;
/**
 *@author CRISTIAN MITUL
 */

public class ExistePalavraException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExistePalavraException(){
		super();
	}
	
	public ExistePalavraException(String message){
		super(message);
	}

}
