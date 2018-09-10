package exceptions;

/**
 *@author CRISTIAN MITUL
 */
public class TrocoInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrocoInexistenteException(){
		super();
	}
	
	public TrocoInexistenteException(String message){
		super(message);
	}

}
