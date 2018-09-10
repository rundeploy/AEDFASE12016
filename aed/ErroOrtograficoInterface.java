package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.io.Serializable;
import java.util.Iterator;


public interface ErroOrtograficoInterface extends Serializable {
	

	/**
	 * @return retorna um iterador de Linhas de um erro ortografico
	 */
	public Iterator<Integer> obterLinhas() ;
	
	/**
	 * Adiciona a linha do texto onde se encontra o erro 
	 * @param linha guarda o numero da linha de um erro
	 */
	public void adicionarLinha(int linha);
	
	/**
	 * Devolve o erro - a palavra que nao existe no dicionario
	 * @return do erro
	 */
	public String recebeErroOrtografico();

}
