package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import exceptions.*;

public interface SpellingInterface extends Serializable{
	
	/**
	 * Adiciona palavras no dicionario (lista de palavras)
	 * @param palavras palavras a adicionar
	 * @throws SemPalavrasNovasException se da lista de palavras a adicionar todas ja existirem no dicionario a excepcao e activada
	 */
	public void adicionarPalavras(List <String> palavras) throws SemPalavrasNovasException;

	/**
	 * Procura a palavra <code>palavra</code> no dicionario
	 * @param palavra palavra a procurar
	 * @throws ErroOrtograficoException se a <code>palavra</code> nao existir no dicionario a excepcao e activada 
	 */
	public void procuraPalavra(String palavra)throws ErroOrtograficoException;
	
	/**
	 * Adiciona um texto
	 * @param idTexto - identificador unico do texto
	 * @param texto - e o proprio texto
	 * @throws IdTextoExistenteException se ja existir um texto com o mesmo identificador a excepcao e activada 
	 */
	public void adicionarTexto(String idTexto, List<String> texto) throws IdTextoExistenteException;

	/**
	 * Remocao de um texto
	 * @param idtexto identificador unico do texto a remover
	 * @throws IdTextoInexistenteException se nao existir o texto com o identificador <code>idtexto</code> a excepcao e activada
	 */
	public void removerTexto(String idtexto) throws IdTextoInexistenteException;

	/**
	 * @param idtexto identificador do texto sobre qual a iteracao e feita
	 * @return Retorna um iterador do texto
	 * @throws IdTextoInexistenteException se nao existir o texto com o identificador <code>idtexto</code> a excepcao e activada
	 */
	public Iterator<String> listarTexto(String idtexto) throws IdTextoInexistenteException;

	/** 
	 * @param idtexto identificador do texto sobre qual a iteracao e feita
	 * @param deLinha o comeco do troco de texto a iterar
	 * @param ateLinha o fim do troco de texto a iterar
	 * @return devolve um iterador com o troco de texto
	 * @throws IdTextoInexistenteException se nao existir o texto com o identificador <code>idtexto</code> a excepcao e activada
	 * @throws IntrevaloMalDefinidoException se <code>deLinha</code> e <code>ateLinha</code> forem incoerentes a excepcao e activada
	 * @throws TrocoInexistenteException se o intervalo com <code>deLinha</code> e <code>ateLinha</code> forem inadequados a excepcao e activada
	 */
	public Iterator<String> listarTrocoTexto(String idtexto, int deLinha, int ateLinha) throws IdTextoInexistenteException, IntrevaloMalDefinidoException, TrocoInexistenteException;

	/**
	 * 
	 * @param idtexto idetificador de um texto sobre qual e analisada a frequencia da palavra <code>palavra</code>
	 * @param palavra parametro para o qual e pretendido saber a quantidade de vezes que aparece no texto
	 * @return retorna a quantidade de vezes que a <code>palavra</code> apareceu num dado texto
	 * @throws IdTextoInexistenteException se nao existir o texto com o identificador <code>idtexto</code> a excepcao e activada
	 */
	public int frequenciaPalavra(String idtexto, String palavra) throws IdTextoInexistenteException;

	/**
	 * 
	 * @param idTexto idetificador de um texto sobre qual e feita a analise de erros
	 * @return devolve um iterador com as palavras que nao estao no dicionario
	 * @throws IdTextoInexistenteException se nao existir o texto com o identificador <code>idtexto</code> a excepcao e activada
	 * @throws SemErrosOrtograficosException devolve caso todas as palavras no texto existirem no dicionario
	 */
	public Iterator<ErroOrtograficoInterface> ListarErrosOrtograficos(String idTexto) throws IdTextoInexistenteException, SemErrosOrtograficosException;

	

	
}
