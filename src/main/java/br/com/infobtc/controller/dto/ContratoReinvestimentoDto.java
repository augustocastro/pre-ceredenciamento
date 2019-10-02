package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.infobtc.model.ContratoReinvestimento;

public class ContratoReinvestimentoDto {

	private Long id;
	private String dt_inicio;
	private String dt_termino;
	private long quantidade_meses;
	private BigDecimal valor;
	private String alinea;
	private Long investimento_id;
	private String status_contrato;
	private String status_financeiro;
	private boolean repassado;
	private String justificativa_reprovacao;
	private BancoDto banco;
	private List<String> arquivos_url = new ArrayList<String>();

	public ContratoReinvestimentoDto() {

	}

	public ContratoReinvestimentoDto(ContratoReinvestimento contratoReinvestimento) {
		this.id = contratoReinvestimento.getId();
		this.dt_inicio = contratoReinvestimento.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contratoReinvestimento.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contratoReinvestimento.getQuantidadeMeses();
		this.valor = contratoReinvestimento.getValor();
		this.banco = new BancoDto(contratoReinvestimento.getBanco());
		this.alinea = contratoReinvestimento.getAlinea();
		this.investimento_id = contratoReinvestimento.getInvestimento().getId();
		this.status_contrato = contratoReinvestimento.getStatusContrato().toString();
		this.status_financeiro = contratoReinvestimento.getStatusFinanceiro().toString();
		this.arquivos_url = contratoReinvestimento.getArquivosUrl();
		this.justificativa_reprovacao = contratoReinvestimento.getJustificativaReprovacao();
		this.repassado = contratoReinvestimento.isRepassado();
	}

	public Long getId() {
		return id;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

	public long getQuantidade_meses() {
		return quantidade_meses;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getAlinea() {
		return alinea;
	}

	public Long getInvestimento_id() {
		return investimento_id;
	}

	public String getStatus_contrato() {
		return status_contrato;
	}

	public String getStatus_financeiro() {
		return status_financeiro;
	}

	public boolean isRepassado() {
		return repassado;
	}
	
	public String getJustificativa_reprovacao() {
		return justificativa_reprovacao;
	}
	
	public BancoDto getBanco() {
		return banco;
	}

	public List<String> getArquivos_url() {
		return arquivos_url;
	}

	public Page<ContratoReinvestimentoDto> converter(Page<ContratoReinvestimento> contratos) {
		return contratos.map(ContratoReinvestimentoDto::new);
	}

	public List<ContratoReinvestimentoDto> converter(List<ContratoReinvestimento> contratos) {
		return contratos.stream().map(ContratoReinvestimentoDto::new).collect(Collectors.toList());
	}
}
