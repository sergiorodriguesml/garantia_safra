package br.quixada.ufc.persistencia.java_json;

import br.quixada.ufc.persistencia.api_sax.SaxAPI;

public class App {
	
	public static void main( String[] args ){
		String arquivo = "saida.xml";
		SaxAPI saxApi = new SaxAPI();
		saxApi.toJason(arquivo);
		System.out.println("::::: FIM APP :::::");
	}
}
