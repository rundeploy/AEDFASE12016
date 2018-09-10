package aed;
/**
 *@author CRISTIAN MITUL
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import exceptions.*;

public class SpellingClasse implements SpellingInterface {

	private static final long serialVersionUID = 1L;

	Map<String, PalavraInterface> mapaDePalavras;
	Map<String, TextoInterface> mapaDeTextos;




	public SpellingClasse(){
		mapaDePalavras = new HashMap<String, PalavraInterface>();
		mapaDeTextos = new HashMap<String, TextoInterface>();

	}

	private void adicionarPalavra(String palavra) throws ExistePalavraException{
		if(mapaDePalavras.containsKey(palavra)){
			throw new ExistePalavraException();			
		}else{
			PalavraInterface p = new PalavraClasse(palavra);
			mapaDePalavras.put(palavra, p);
			mapaDeTextos = new HashMap<String, TextoInterface>();
		}
	}


	public void adicionarPalavras(List <String> palavras) throws SemPalavrasNovasException{
		Iterator<String> itpals = palavras.iterator();
		int numPalavrasAdcndas = 0;
		while(itpals.hasNext()){
			try {
				adicionarPalavra(itpals.next());
				numPalavrasAdcndas++;
			} catch (Exception e) {
				//nao fazer nada porque pois so preciso saber se foi adicionado pelo menos uma palavra 
			}
		}
		
		if(numPalavrasAdcndas == 0 ){
			throw new SemPalavrasNovasException();
		}
	}


	public void procuraPalavra(String palavra) throws ErroOrtograficoException{
		if(!mapaDePalavras.containsKey(palavra)){
			throw new ErroOrtograficoException();
		}
	}

	public void adicionarTexto(String idTexto, List<String> texto) throws IdTextoExistenteException{
		if(mapaDeTextos.containsKey(idTexto)){
			throw new IdTextoExistenteException();
		}else{
			TextoInterface t = new TextoClasse(idTexto, texto);
			mapaDeTextos.put(idTexto, t);
		}
	}

	@Override
	public void removerTexto(String idtexto) throws IdTextoInexistenteException {
		if(!mapaDeTextos.containsKey(idtexto)){
			throw new IdTextoInexistenteException();
		}else{
			mapaDeTextos.remove(idtexto);
		}
	}

	@Override
	public Iterator<String> listarTexto(String idtexto) throws IdTextoInexistenteException {
		if(!mapaDeTextos.containsKey(idtexto)){
			throw new IdTextoInexistenteException();
		}else{
			return mapaDeTextos.get(idtexto).listarTexto();
		}
	}

	@Override
	public Iterator<String> listarTrocoTexto(String idtexto, int deLinha, int ateLinha) throws IdTextoInexistenteException, IntrevaloMalDefinidoException, TrocoInexistenteException {
		if(!mapaDeTextos.containsKey(idtexto)){
			throw new IdTextoInexistenteException();
		}else{
			return mapaDeTextos.get(idtexto).listartrocoTexto(deLinha,ateLinha);
		}
	}

	@Override
	public int frequenciaPalavra(String idtexto, String palavra) throws IdTextoInexistenteException {
		if(!mapaDeTextos.containsKey(idtexto)){
			throw new IdTextoInexistenteException();
		}else{
			return mapaDeTextos.get(idtexto).frequenciaPalavra(palavra);
		}		
	}
	
	public Iterator<ErroOrtograficoInterface> ListarErrosOrtograficos(String idTexto) throws IdTextoInexistenteException, SemErrosOrtograficosException{
		
		if(!mapaDeTextos.containsKey(idTexto)){
			throw new IdTextoInexistenteException();
		}
		
			return  mapaDeTextos.get(idTexto).listarErrosOrtograficos( mapaDePalavras);

			
		
	}

}
