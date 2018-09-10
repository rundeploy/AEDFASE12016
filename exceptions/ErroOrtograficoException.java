package exceptions;
/**
 *@author CRISTIAN MITUL 
 */

public class ErroOrtograficoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ErroOrtograficoException(){
		super();
	}
	
	public ErroOrtograficoException(String message){
		super(message);
	}

}
