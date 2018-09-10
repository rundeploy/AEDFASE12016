package exceptions;
/**
 *@author CRISTIAN MITUL
 */

public class IdTextoExistenteException extends Exception {

	
private static final long serialVersionUID = 1L;
	
	public IdTextoExistenteException(){
		super();
	}
	
	public IdTextoExistenteException(String message){
		super(message);
	}
}
