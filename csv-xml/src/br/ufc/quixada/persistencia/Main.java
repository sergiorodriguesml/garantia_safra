package br.ufc.quixada.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufc.quixada.persistencia.classes.*;

public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		String entrada = "GarantiaSafra_test.csv" ;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada)));

		String nomeM="",codM="",estado="",ano="",linha="";
		String[] elementos;

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();	
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();	
		Document doc = docBuilder.newDocument();
		Element root = doc.createElement("root");
		doc.appendChild(root);	
		Element safra = null;
		Element uf = null;
		Element municipio = null;
		Element beneficiarios = null;

		int i=0;
		while(br.ready() && i<10000){
			linha = br.readLine();
			elementos = linha.split(";");
			if(!ano.equals(elementos[0])){
				ano = elementos[0];
				safra = doc.createElement("GarantiaSafra");
				Attr registro = doc.createAttribute("registro");
				registro.setValue(ano);
				safra.setAttributeNode(registro);
				root.appendChild(safra);
			}
			if(!estado.equals(elementos[1])){
				//logica com estado repetido
				estado = elementos[1];
				uf = doc.createElement("estado");
				Attr sigla = doc.createAttribute("sigla");
				sigla.setValue(elementos[1]);
				uf.setAttributeNode(sigla);
				safra.appendChild(uf);
			}
			if(!codM.equals(elementos[2])){
				codM = elementos[2];
				nomeM = elementos[3];
				municipio = doc.createElement("municipio");
				Attr cod = doc.createAttribute("cod");
				cod.setValue(codM);
				municipio.setAttributeNode(cod);
				Element nome = doc.createElement("nome");
				nome.appendChild(doc.createTextNode(nomeM));
				municipio.appendChild(nome);
				uf.appendChild(municipio);
				beneficiarios = doc.createElement("beneficiarios");
				municipio.appendChild(beneficiarios);
			}
			
			Element beneficiario = doc.createElement("beneficiario");
			beneficiarios.appendChild(beneficiario);
			
			Element nis = doc.createElement("nis");
			nis.appendChild(doc.createTextNode(elementos[4]));
			beneficiario.appendChild(nis);
			
			Element bNome = doc.createElement("nome");
			bNome.appendChild(doc.createTextNode(elementos[5]));
			beneficiario.appendChild(bNome);
			
			Element valor = doc.createElement("valor");
			valor.appendChild(doc.createTextNode(elementos[6]));
			beneficiario.appendChild(valor);	

//			System.out.println("====================");
//			for(String elemento:elementos){
//				System.out.println(elemento);
//
//			}
//			System.out.println("====================");
			i++;
		}
		TransformerFactory transformerFactory =	TransformerFactory.newInstance();	
		Transformer transformer	= transformerFactory.newTransformer();	
		DOMSource source = new DOMSource(doc);	
		StreamResult result	= new StreamResult(new File("saida.xml"));
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");	
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",	
		"2");	
		transformer.transform(source,result);
		System.out.println("FIM");
	}

}
