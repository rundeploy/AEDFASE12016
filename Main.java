import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import aed.ErroOrtograficoInterface;
import aed.SpellingClasse;
import aed.SpellingInterface;
import exceptions.*;

public class Main {
	
	private static final String ADICIONA_PALAVRA = "AD";
	private static final String ATUALIZACAO_DICIONARIO_SUC = "Atualizacao do dicionario com sucesso.\n";
	private static final String N_TEM_PALAVRAS_NOVAS = "Lista de palavras nao contem palavras novas.\n";
	
	private static final String PESQUISAR_PALAVRA = "PC";
	private static final String PALAVRA_CORRETA = "Palavra correta.\n";
	private static final String ERRO_ORTOGRAFICO = "Erro ortografico.\n";
	
	private static final String ADICIONAR_TEXTO = "AT";
	private static final String TEXTO_ADICIONADO_SUC = "Texto adicionado com sucesso.\n";
	private static final String ID_TEXTO_EXISTENTE = "Identificador de texto existente.\n";
	
	private static final String REMOVER_TEXTO = "RT";
	private static final String REMOCAO_TEXTO_SUC = "Remocao de texto com sucesso.\n";
	private static final String TEXTO_INEXISTENTE_RT = "Texto inexistente.\n";
	
	private static final String LISTAR_TEXTO = "LT";
	private static final String TEXTO_INEXISTENTE_LT = "Texto inexistente.\n";
	
	private static final String LISTAR_TROCO_TEXTO = "LR"; 
	private static final String TEXTO_INEXISTENTE_RR = "Texto inexistente.\n";
	private static final String INTERAVALO_MAL_DEFINIDO = "Intervalo de numero de linhas mal definido.\n";
	private static final String TROCO_INEXISTENTE = "Troco inexistente.\n";
	
	private static final String LISTAR_ERROS_ORTOGRAFICOS = "LE";
	private static final String TEXTO_INEXISTENTE_LE = "Texto inexistente.\n";
	private static final String INEXISTENCIA_DE_ERROS_ORTOGRAFICOS = "Inexistencia de erros ortograficos no texto.\n";
	
	private static final String FREQ_PALAVRA_EM_TEXTO = "FP";
	private static final String PALVRA_OCORRE = " ocorre ";
	private static final String VEZES_NO_TEXTO = " vezes no texto referido.\n";
	private static final String TEXTO_INEXISTENTE_FP = "Texto inexistente.\n";
	
	/**
	 *@author CRISTIAN MITUL
	 */
	

	public static void main(String[] args){
		/**Linha de comandos*/
		SpellingInterface spell = load("testedegravarcoisasnodisco.aed");
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			String comando = in.next();
			
			if(comando.equalsIgnoreCase(ADICIONA_PALAVRA)){
				adicionarPalavra(spell,in);
			}else if(comando.equalsIgnoreCase(ADICIONAR_TEXTO)){
				adicionarTexto(spell, in);
			}else if(comando.equalsIgnoreCase(PESQUISAR_PALAVRA)){
				pesquisarPalavra(spell, in);
			}else if(comando.equalsIgnoreCase(REMOVER_TEXTO)){
				removerTexto(spell, in);
			}else if(comando.equalsIgnoreCase(LISTAR_TEXTO)){
				listarTexto(spell, in);
			}else if(comando.equalsIgnoreCase(LISTAR_TROCO_TEXTO)){
				listarTrocoTexto(spell, in);
			}else if(comando.equalsIgnoreCase(LISTAR_ERROS_ORTOGRAFICOS)){
				listarErrosOrtograficos(spell, in);
			}else if(comando.equalsIgnoreCase(FREQ_PALAVRA_EM_TEXTO)){
				frequenciaPalavra(spell, in);
			}	
		}
		store(spell,"testedegravarcoisasnodisco.aed");
	}
	
	/**Adicionar uma palavra.*/
	private static void adicionarPalavra(SpellingInterface spell, Scanner in) {
		
		int numPalavras = in.nextInt();
		in.nextLine();
		
		List<String> palavras = new ArrayList<String>();
		
		for(int i = 0; i<numPalavras; i++){
			palavras.add(in.nextLine().toLowerCase());
		}
		try {
			spell.adicionarPalavras(palavras);
			System.out.println(ATUALIZACAO_DICIONARIO_SUC);
		} catch (SemPalavrasNovasException e) {
			System.out.println(N_TEM_PALAVRAS_NOVAS);
		}
	}
	
	
	
	
	/**Adicionar um texto.*/
	private static void adicionarTexto(SpellingInterface spell, Scanner in) {
		String idTexto = in.next();
		int numLinhas = in.nextInt();
		in.nextLine();
		List <String> texto = new ArrayList<String>();
		
		for ( int i = 0; i < numLinhas; i++){
			texto.add(in.nextLine());
		}
		
		try{
			spell.adicionarTexto(idTexto,texto);
			System.out.println(TEXTO_ADICIONADO_SUC);
		}catch(IdTextoExistenteException e){
			System.out.println(ID_TEXTO_EXISTENTE);
		}
	}
	
	
	/**Pesquisar a existencia duma palavra no dicionario.*/
	private static void pesquisarPalavra(SpellingInterface spell, Scanner in) {
		String palavra = in.next();
		in.nextLine();
		
		try{
			spell.procuraPalavra(palavra.toLowerCase());
			System.out.println(PALAVRA_CORRETA);
		}catch(ErroOrtograficoException e){
			System.out.println(ERRO_ORTOGRAFICO);
		}
	}
	
	/**Remove um dado texto.*/
	private static void removerTexto(SpellingInterface spell, Scanner in){
		String idtexto = in.next();
		in.nextLine();
		try {
			spell.removerTexto(idtexto);
			System.out.println(REMOCAO_TEXTO_SUC);
		} catch (IdTextoInexistenteException e) {
			System.out.println(TEXTO_INEXISTENTE_RT);
		}
	}
	
	/**Imprimir um texto completo.*/
	private static void listarTexto(SpellingInterface spell, Scanner in) {
		String idtexto = in.next();
		in.nextLine();
		try {
			Iterator <String> ittexto = spell.listarTexto(idtexto);
			while(ittexto.hasNext()){
				System.out.println(ittexto.next());
			}
			System.out.println();
		} catch (IdTextoInexistenteException e) {
			System.out.println(TEXTO_INEXISTENTE_LT);
		}
	}
	
	
	/**Imprimir uma parte do texto indicando as linhas de inicio e o fim.*/
	private static void listarTrocoTexto(SpellingInterface spell, Scanner in) {
		String idtexto = in.next();
		int deLinha = in.nextInt();
		int ateLinha = in.nextInt();
		in.nextLine();
		
		try {
			Iterator<String> ittexto = spell.listarTrocoTexto(idtexto,deLinha,ateLinha);
			while(ittexto.hasNext()){
				System.out.println(ittexto.next());
			}
			System.out.println();
		} catch (IdTextoInexistenteException e) {
			System.out.println(TEXTO_INEXISTENTE_RR);
		} catch (IntrevaloMalDefinidoException e) {
			System.out.println(INTERAVALO_MAL_DEFINIDO);
		} catch (TrocoInexistenteException e) {
			System.out.println(TROCO_INEXISTENTE);
		}
		
	}
	
	/**Listagem das palavras nao contidas no dicionario.*/
	private static void listarErrosOrtograficos(SpellingInterface spell, Scanner in) {
		String idTexto = in.next();
		in.nextLine();
		//try{
			//System.out.println(spell.retornaErroOrtografico(idTexto));
		Iterator<ErroOrtograficoInterface> erros;
		try {
			erros = spell.ListarErrosOrtograficos(idTexto);
			
			while(erros.hasNext()){
				ErroOrtograficoInterface erro = erros.next();
				System.out.println(erro.recebeErroOrtografico());
				Iterator<Integer> linhas = erro.obterLinhas();
				while(linhas.hasNext()){
					System.out.println(linhas.next());
				}
				System.out.println();
			}
		} catch (IdTextoInexistenteException e) {
			System.out.println(TEXTO_INEXISTENTE_LE);
		} catch (SemErrosOrtograficosException e) {
			System.out.println(INEXISTENCIA_DE_ERROS_ORTOGRAFICOS);
		}	
	}
	
	
	/**Numero de ocorrencias de uma palavra num texto.*/
	private static void frequenciaPalavra(SpellingInterface spell, Scanner in){
		String idtexto = in.next();
		String palavra = in.next();
		in.nextLine();
		try {
			System.out.println(palavra + PALVRA_OCORRE + spell.frequenciaPalavra(idtexto,palavra) + VEZES_NO_TEXTO);
		} catch (IdTextoInexistenteException e) {
			System.out.println(TEXTO_INEXISTENTE_FP);
		}
	}
	
	
	/**Guarda o estado do sistema num ficheiro para posteriormente ser carregado.*/
    private static void store(SpellingInterface spell,String filename){
        ObjectOutputStream file;
        try {
            file = new ObjectOutputStream(new FileOutputStream(filename));
            file.writeObject(spell);
            file.flush();
            file.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /**Carrega o estado do sistema que foi guardado num ficheiro anteriormente.*/
    private static SpellingInterface load(String filename){
    	SpellingClasse spell = null;
        try{
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename));
            spell = (SpellingClasse) file.readObject();
            file.close();
            
        }catch( IOException e ){
            spell = new SpellingClasse();
        }catch(ClassNotFoundException e ){
            e.printStackTrace();
            System.exit(0);
        }
        return spell;
    }
}