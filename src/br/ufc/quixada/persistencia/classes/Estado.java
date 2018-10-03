package br.ufc.quixada.persistencia.classes;

import java.util.ArrayList;

public class Estado {
	private String sigla;
	private ArrayList<Municipio> municipios;
	
	public Estado() {}

	public Estado(String sigla, ArrayList<Municipio> municipios) {
		super();
		this.sigla = sigla;
		this.municipios = municipios;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ArrayList<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(ArrayList<Municipio> municipios) {
		this.municipios = municipios;
	}

	@Override
	public String toString() {
		return "Estado [sigla=" + sigla + ", municipios=" + municipios.toString() + "]";
	}
}
