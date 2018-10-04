package br.quixada.ufc.persistencia.api_sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.quixada.ufc.persistencia.classes.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxAPI extends DefaultHandler {

	private String tagAtual;
	private String siglaAtual;
	private GarantiaSafra safraTemp;
	private Estado estadoTemp;
	private Municipio municipioTemp;
	private Beneficiario beneficiarioTemp;

	/**
	 * construtor default
	 */
	public SaxAPI() {}

	/**
	 * Método que executa o parsing: laço automático que varre o documento de
	 * início ao fim, disparando eventos relevantes
	 * 
	 * @param pathArq
	 */
	public void fazerParsing(String arquivo) {

		// Passo 1: cria instância da classe SAXParser, através da factory
		// SAXParserFactory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;

		try {
			sp = spf.newSAXParser();

			// Passo 2: comanda o início do parsing
			sp.parse(arquivo, this); // o "this" indica que a própria
			// classe "DevmediaSAX" atuará como
			// gerenciadora de eventos SAX.

			// Passo 3: tratamento de exceções.
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// os métodos startDocument, endDocument, startElement, endElement e
	// characters, listados a seguir, representam os métodos de call-back da API
	// SAX

	/**
	 * evento startDocument do SAX. Disparado antes do processamento da primeira
	 * linha
	 */
	public void startDocument() {
		System.out.println("\nIniciando o Documento...\n");
	}

	/**
	 * evento endDocument do SAX. Disparado depois do processamento da última
	 * linha
	 */
	public void endDocument() {
		System.out.println("\nFim do Documento...");
	}

	/**
	 * evento startElement do SAX. disparado quando o processador SAX identifica
	 * a abertura de uma tag. Ele possibilita a captura do nome da tag e dos
	 * nomes e valores de todos os atributos associados a esta tag, caso eles
	 * existam.
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {

		// recupera o nome da tag atual
		tagAtual = qName;

		// se a tag for "<pais>", recupera o valor do atributo "sigla"
		if (qName.compareTo("pais") == 0) {
			siglaAtual = atts.getValue(0);
		}
	}

	/**
	 * evento endElement do SAX. Disparado quando o processador SAX identifica o
	 * fechamento de uma tag (ex: )
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		tagAtual = "";
	}

	/**
	 * evento characters do SAX. É onde podemos recuperar as informações texto
	 * contidas no documento XML (textos contidos entre tags). Neste exemplo,
	 * recuperamos os nomes dos países, a população e a moeda
	 * 
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String texto = new String(ch, start, length);

		// ------------------------------------------------------------
		// --- TRATAMENTO DAS INFORMAÇÕES DE ACORDO COM A TAG ATUAL ---
		// ------------------------------------------------------------

		if (tagAtual.compareTo("nome") == 0) {

			System.out.print(texto + " - SIGLA: " + siglaAtual);
		}

		if (tagAtual.compareTo("moeda") == 0) {

			System.out.print(" - MOEDA: " + texto);
		}

		if (tagAtual.compareTo("populacao") == 0) {

			System.out.println(" - POPULACAO: " + texto);
		}
	}
}