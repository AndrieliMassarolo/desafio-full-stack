package br.edu.unoesc.desafiofullstack.model;

public enum Sexo {

	M("Masculino"), F("Feminino"), O("Outros");

	private final String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
