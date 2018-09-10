package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ErroOrtograficoClasse implements ErroOrtograficoInterface {
	
	
	private static final long serialVersionUID = 1L;
	private String erroOrtografico;
	Set<Integer> linhas = new HashSet<Integer>();
	
	public ErroOrtograficoClasse(String erroOrtografico){
		this.erroOrtografico =  erroOrtografico;
	}

	public Iterator<Integer> obterLinhas() {
		return linhas.iterator();
	}

	public void adicionarLinha(int linha) {
		this.linhas.add(linha);
	}

	public String recebeErroOrtografico() {
		return erroOrtografico;
	}
	
	
}
