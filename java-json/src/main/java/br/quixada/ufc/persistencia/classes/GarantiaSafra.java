package br.quixada.ufc.persistencia.classes;

import java.util.ArrayList;

public class GarantiaSafra {
	private String registro;
	private ArrayList<Estado> estados;
	
	public GarantiaSafra() {}

	public GarantiaSafra(String registro, ArrayList<Estado> estados) {
		super();
		this.registro = registro;
		this.estados = estados;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}

	@Override
	public String toString() {
		return "GarantiaSafra [registro=" + registro + ", estados=" + estados.toString() + "]";
	}
	
}
