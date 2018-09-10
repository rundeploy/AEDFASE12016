package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.io.Serializable;
import java.util.Iterator;


import java.util.Map;

import exceptions.IntrevaloMalDefinidoException;
import exceptions.SemErrosOrtograficosException;
import exceptions.TrocoInexistenteException;

public interface TextoInterface extends Serializable {

	/**
	 * @return  Devolve um iterador de texto
	 */
	Iterator<String> listarTexto();

	/**
	 * @param deLinha o numero da linha a partir da qual e pretendido imprimir o troco de texto (linha minimo)
	 * @param ateLinha o numero da linha maximo pretendido a ser impresso (linha maximo)
	 * @return devolve e retornado um iterador com o troco de texto pretendido
	 * @throws IntrevaloMalDefinidoException e retornado se o <code>deLinha</code> e <code>ateLinha</code> nao forem coerentes
	 * @throws TrocoInexistenteException e retornado se o <code>deLinha</code> e <code>ateLinha</code> adequados para o texto dado
	 */
	Iterator<String> listartrocoTexto(int deLinha, int ateLinha) throws IntrevaloMalDefinidoException, TrocoInexistenteException;

	/**
	 * @param palavra parametro para o qual e pretendido saber a quantidade de vezes que aparece no texto
	 * @return retorna a quantidade de vezes que a <code>palavra</code> apareceu num dado texto
	 */
	int frequenciaPalavra(String palavra);

	/** 
	 * @param mapaDePalavras o sitio onde e procurada a palavra para determinar se e erro ou nao
	 * @return devolve um iterador com as palavras que nao estao no <code>mapaDePalavras</code>
	 * @throws SemErrosOrtograficosException devolve caso todas as palavras no texto existirem no <code>mapaDePalavras</code>
	 */
	Iterator<ErroOrtograficoInterface> listarErrosOrtograficos(Map<String, PalavraInterface> mapaDePalavras) throws SemErrosOrtograficosException;

}
