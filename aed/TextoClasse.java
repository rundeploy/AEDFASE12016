package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import exceptions.IntrevaloMalDefinidoException;
import exceptions.SemErrosOrtograficosException;
import exceptions.TrocoInexistenteException;

public class TextoClasse implements TextoInterface{

	private static final long serialVersionUID = 1L;
	
	private String idTexto;
	private List<String> texto;
	private Map<String, ErroOrtograficoInterface> erros;
	
	public TextoClasse(String idTexto,List<String> texto){
		this.idTexto=idTexto;
		this.texto = texto;
		erros = new HashMap<String, ErroOrtograficoInterface>();
	}


	public Iterator<String> listarTexto() {
		return this.texto.iterator();
	}


	public Iterator<String> listartrocoTexto(int deLinha, int ateLinha) throws IntrevaloMalDefinidoException, TrocoInexistenteException {
		
		if(ateLinha == 0){
			ateLinha = texto.size();
		}
		
		if(ateLinha < deLinha){
			throw new IntrevaloMalDefinidoException();
		}
		
		if((deLinha > texto.size()) || (ateLinha > texto.size())){
			throw new TrocoInexistenteException();
		}
		
		List <String> tmptexto = new ArrayList<String>();
		for(int i= deLinha ; i <= ateLinha; i++){
			tmptexto.add(texto.get(i-1));
		}
		return tmptexto.iterator();
	}

	
	public int frequenciaPalavra(String palavra) {
		int frequencia = 0;
		Iterator<String> ittexto = texto.iterator();
		String [] palavras = null;
		
		while(ittexto.hasNext()){
			String linha = ittexto.next();
			palavras = linha.split(" ");
			for(int i = 0; i < palavras.length ; i++){
				if(palavras[i].equalsIgnoreCase(palavra)){
					frequencia++;
				}
			}
		}
		
		return frequencia;
	}
	
	 
	 public Iterator<ErroOrtograficoInterface> listarErrosOrtograficos(Map<String, PalavraInterface> mapaDePalavras) throws SemErrosOrtograficosException {
		 	Iterator<String> ittexto = texto.iterator();
			String [] palavrasDoTexto = null;
			
			int contadorLinha = 0 ;
			while(ittexto.hasNext()){
				String linha = ittexto.next();
				contadorLinha++;
				linha = linha.trim();
				if(linha.isEmpty()){
					linha = ittexto.next();
					linha = linha.trim();
				}
				palavrasDoTexto = linha.split("\\s+");
				for(int i = 0; i < palavrasDoTexto.length ; i++){
					if( !mapaDePalavras.containsKey(palavrasDoTexto[i].toLowerCase()) ){
						if(erros.containsKey(palavrasDoTexto[i])){
							erros.get(palavrasDoTexto[i]).adicionarLinha(contadorLinha);
						}else{
							erros.put(palavrasDoTexto[i], new ErroOrtograficoClasse(palavrasDoTexto[i]));
							erros.get(palavrasDoTexto[i]).adicionarLinha(contadorLinha);
						}
					}
						
				}
			}
			if(erros.size() == 0){
				throw new SemErrosOrtograficosException();
			}
			
			return erros.values().iterator();
	 }
	 
	
	
	
	

}
