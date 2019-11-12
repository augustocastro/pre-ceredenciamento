package br.com.infobtc.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.StatusRepasse;
import br.com.infobtc.model.TipoRecebedor;

public class RepasseParcelaVo {

	/*
	 * Relatório de repasses Deve ser selecionado o tipo de repasse a ser exibido
	 * (investidor/consultor/escritório) Apresenta a relação de repasses por
	 * situação (executado, a executar e atrasado) dentro de uma data especifica,
	 * por consultor quando o perfil for gerencial, sempre apresentando os
	 * totalizadores. Contém o número dos contratos repassados, nome do investidor,
	 * valor de contrato, data do repasse
	 */

	private TipoRecebedor tipo_recebedor;
	private StatusRepasse status_repasse;
	private int numero_parcela;
	private String dt_realizacao_repasse;
	private String dt_parcela;
	private String repassador;
	private String investidor;
	private long contrato_id;
	private long parcela_id;
	private double valor_contrato;

	public RepasseParcelaVo(TipoRecebedor tipo_recebedor, StatusRepasse status_repasse, int numero_parcela,
			LocalDate dt_realizacao_repasse, LocalDate dt_parcela, String repassador, String investidor,
			long contrato_id, long parcela_id, BigDecimal valor_contrato) {
	
		this.tipo_recebedor = tipo_recebedor;
		this.status_repasse = status_repasse;
		this.numero_parcela = numero_parcela;
		this.dt_realizacao_repasse = dt_realizacao_repasse != null ? dt_realizacao_repasse.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")): null;
		this.dt_parcela = dt_parcela.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.repassador = repassador;
		this.investidor = investidor;
		this.contrato_id = contrato_id;
		this.parcela_id = parcela_id;
		this.valor_contrato = valor_contrato.doubleValue();
	}

	public TipoRecebedor getTipo_recebedor() {
		return tipo_recebedor;
	}

	public StatusRepasse getStatus_repasse() {
		return status_repasse;
	}

	public int getNumero_parcela() {
		return numero_parcela;
	}

	public String getDt_realizacao_repasse() {
		return dt_realizacao_repasse;
	}

	public String getDt_parcela() {
		return dt_parcela;
	}

	public String getRepassador() {
		return repassador;
	}

	public String getInvestidor() {
		return investidor;
	}

	public long getContrato_id() {
		return contrato_id;
	}

	public long getParcela_id() {
		return parcela_id;
	}

	public double getValor_contrato() {
		return valor_contrato;
	}

}
