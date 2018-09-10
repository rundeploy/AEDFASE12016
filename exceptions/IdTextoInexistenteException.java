package exceptions;

/**
 *@author CRISTIAN MITUL
 */
public class IdTextoInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IdTextoInexistenteException(){
		super();
	}
	
	public IdTextoInexistenteException(String message){
		super(message);
	}
}
