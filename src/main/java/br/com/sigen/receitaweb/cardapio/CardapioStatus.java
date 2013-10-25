package br.com.sigen.receitaweb.cardapio;

public enum CardapioStatus {

	AVAL("Avaliação Cliente"), PEND_SUBST("Pendente Substituição"), APROV(
			"Aprovado");

	private String valor;

	CardapioStatus(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
