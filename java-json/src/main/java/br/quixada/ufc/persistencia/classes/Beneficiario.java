package br.quixada.ufc.persistencia.classes;

public class Beneficiario {
	private String nis;
	private String nome;
	private String valor;
	
	public Beneficiario(){}
		
	public Beneficiario(String nis, String nome, String valor) {
		this.nis = nis;
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNis() {
		return nis;
	}
	public void setNis(String nis) {
		this.nis = nis;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Beneficiario [nis=" + nis + ", nome=" + nome + ", valor=" + valor + "]";
	}	
}
