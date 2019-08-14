package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.infobtc.model.ContratoInvestimento;

public class ContratoInvestimentoDetalhadoDto {

	private Long id;
	private String nome;
	private String data_inicio;
	private String dt_termino;
	private long quantidade_meses;
	private BigDecimal valor;
	private boolean valid;
	private InvestidorDto investidor;
	private BancoDto banco;
	private List<ContratoReinvestimentoDto> reinvestimentos = new ArrayList<ContratoReinvestimentoDto>();

	public ContratoInvestimentoDetalhadoDto() {
	}

	public ContratoInvestimentoDetalhadoDto(ContratoInvestimento contratoInvestimento) {
		this.id = contratoInvestimento.getId();
		this.nome = contratoInvestimento.getNome();
		this.data_inicio = contratoInvestimento.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contratoInvestimento.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contratoInvestimento.getQuantidadeMeses();
		this.valor = contratoInvestimento.getValor();
		this.investidor = new InvestidorDto(contratoInvestimento.getInvestidor());
		this.banco = new BancoDto(contratoInvestimento.getBanco());
		this.reinvestimentos = new ContratoReinvestimentoDto().converter(contratoInvestimento.getReinvestimentos());
		this.valid = contratoInvestimento.isValid();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getData_inicio() {
		return data_inicio;
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

	public InvestidorDto getInvestidor() {
		return investidor;
	}

	public BancoDto getBanco() {
		return banco;
	}

	public List<ContratoReinvestimentoDto> getReinvestimentos() {
		return reinvestimentos;
	}

	public boolean isValid() {
		return valid;
	}

	public Page<ContratoInvestimentoDetalhadoDto> converter(Page<ContratoInvestimento> contratos) {
		return contratos.map(ContratoInvestimentoDetalhadoDto::new);
	}

}
