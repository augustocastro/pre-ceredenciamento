package br.com.infobtc.controller.dto;

import java.util.List;

public class ContaReceberFinanceiroDto {

	private int qt_contratos;
	private double valor_liquido;
	private List<Long> ids_contratos;

	public ContaReceberFinanceiroDto(int quantidade_contratos, double total_liquido_contratos, List<Long> ids_contratos) {
		this.qt_contratos = quantidade_contratos;
		this.valor_liquido = total_liquido_contratos;
		this.ids_contratos = ids_contratos;
	}

	public int getQt_contratos() {
		return qt_contratos;
	}

	public double getValor_liquido() {
		return valor_liquido;
	}

	public List<Long> getIds_contratos() {
		return ids_contratos;
	}

}
