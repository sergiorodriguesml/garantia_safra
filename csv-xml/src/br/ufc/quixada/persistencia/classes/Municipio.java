package br.ufc.quixada.persistencia.classes;

import java.util.ArrayList;

public class Municipio {
	private String cod;
	private String nome;
	private ArrayList<Beneficiario> beneficiarios;
	
	public Municipio(){}
	
	public Municipio(String cod, String nome, ArrayList<Beneficiario> beneficiarios) {
		this.cod = cod;
		this.nome = nome;
		this.beneficiarios = beneficiarios;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(ArrayList<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	@Override
	public String toString() {
		return "Municipio [cod=" + cod + ", nome=" + nome + ", beneficiarios=" + beneficiarios.toString() + "]";
	}
	
	
}
