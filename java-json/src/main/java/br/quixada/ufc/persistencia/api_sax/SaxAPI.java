package br.quixada.ufc.persistencia.api_sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.quixada.ufc.persistencia.classes.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SaxAPI extends DefaultHandler {

	private String tagAtual;
	private String tagPai;
	private GarantiaSafra safraTemp;
	private Estado estadoTemp;
	private Municipio municipioTemp;
	private Beneficiario beneficiarioTemp;
	private ArrayList<Beneficiario> beneficiarios;
	private ArrayList<Municipio> municipios;
	private ArrayList<Estado> estados;
	private ArrayList<GarantiaSafra> garantiaSafra;

	public SaxAPI() {}
	
	@Override
	public String toString() {
		return "SaxAPI [garantiaSafra=" + garantiaSafra + "]";
	}

	public void Parsing(String arquivo) {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;

		try {
			sp = spf.newSAXParser();
			sp.parse(arquivo, this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public void startDocument() {
		System.out.println("\nIniciando o Documento...\n");
		garantiaSafra = new ArrayList<GarantiaSafra>();
	}

	public void endDocument() {
		System.out.println("\nFim do Documento...");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) {

		tagAtual = qName;

		if (qName.equalsIgnoreCase("GarantiaSafra")) {
			safraTemp = new GarantiaSafra();
			safraTemp.setRegistro(atts.getValue(0));
			estados = new ArrayList<Estado>();
		}
		else if (qName.equalsIgnoreCase("estado")) {
			estadoTemp = new Estado();
			estadoTemp.setSigla(atts.getValue(0));
			municipios = new ArrayList<Municipio>();
		}
		else if (qName.equalsIgnoreCase("municipio")) {
			tagPai = "municipio";
			municipioTemp = new Municipio();
			municipioTemp.setCod(atts.getValue(0));
			beneficiarios = new ArrayList<Beneficiario>();
		}
		else if (qName.equalsIgnoreCase("beneficiario")) {
			tagPai = "beneficiario";
			beneficiarioTemp = new Beneficiario();
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		tagAtual = "";
		
		if (qName.equalsIgnoreCase("GarantiaSafra")) {
			safraTemp.setEstados(estados);
			garantiaSafra.add(safraTemp);
		}
		else if (qName.equalsIgnoreCase("estado")) {
			estadoTemp.setMunicipios(municipios);
			estados.add(estadoTemp);
		}
		else if (qName.equalsIgnoreCase("municipio")) {
			municipioTemp.setBeneficiarios(beneficiarios);
			municipios.add(municipioTemp);		
		}
		else if (qName.equalsIgnoreCase("beneficiario")) {
			beneficiarios.add(beneficiarioTemp);
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String texto = new String(ch, start, length);

		if (tagAtual.equalsIgnoreCase("nome")) {
			if(tagPai.equalsIgnoreCase("municipio")) municipioTemp.setNome(texto);
			else if(tagPai.equalsIgnoreCase("beneficiario")) beneficiarioTemp.setNome(texto);
		}

		if (tagAtual.equalsIgnoreCase("nis")) {
			beneficiarioTemp.setNis(texto);
		}

		if (tagAtual.equalsIgnoreCase("valor")) {
			beneficiarioTemp.setValor(texto);
		}
	}

	
	public void toJason(String arquivo){
		Parsing(arquivo);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("resultado.json"), garantiaSafra);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}