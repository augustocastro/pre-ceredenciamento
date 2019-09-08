package br.com.infobtc.controller.dto;

public class ContaReceberFinanceiroDto {

	private int qt_contratos;
	private double valor_liquido;

	public ContaReceberFinanceiroDto(int quantidade_contratos, double total_liquido_contratos) {
		this.qt_contratos = quantidade_contratos;
		this.valor_liquido = total_liquido_contratos;
	}

	public int getQt_contratos() {
		return qt_contratos;
	}

	public double getValor_liquido() {
		return valor_liquido;
	}

}
