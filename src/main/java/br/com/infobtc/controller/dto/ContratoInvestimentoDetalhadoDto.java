package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.infobtc.model.ContratoInvestimento;

public class ContratoInvestimentoDetalhadoDto {

	private Long id;
	private String dt_inicio;
	private String dt_termino;
	private long quantidade_meses;
	private BigDecimal valor;
	private boolean valid1;
	private boolean valid2;
	private String tipo_rendimento;
	private InvestidorDto investidor;
	private ConsultorDto consultor;
	private BancoDto banco;
	private List<ContratoReinvestimentoDto> reinvestimentos = new ArrayList<ContratoReinvestimentoDto>();

	public ContratoInvestimentoDetalhadoDto() {
	}

	public ContratoInvestimentoDetalhadoDto(ContratoInvestimento contratoInvestimento) {
		this.id = contratoInvestimento.getId();
		this.dt_inicio = contratoInvestimento.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contratoInvestimento.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contratoInvestimento.getQuantidadeMeses();
		this.valor = contratoInvestimento.getValor();
		this.investidor = new InvestidorDto(contratoInvestimento.getInvestidor());
		this.consultor = new ConsultorDto(contratoInvestimento.getConsultor());
		this.banco = new BancoDto(contratoInvestimento.getBanco());
		this.reinvestimentos = new ContratoReinvestimentoDto().converter(contratoInvestimento.getReinvestimentos());
		this.valid1 = contratoInvestimento.isValid1();
		this.valid2 = contratoInvestimento.isValid2();
		this.tipo_rendimento = contratoInvestimento.getTipoRendimento();
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

	public InvestidorDto getInvestidor() {
		return investidor;
	}
	
	public ConsultorDto getConsultor() {
		return consultor;
	}

	public BancoDto getBanco() {
		return banco;
	}

	public List<ContratoReinvestimentoDto> getReinvestimentos() {
		return reinvestimentos;
	}

	public boolean isValid1() {
		return valid1;
	}
	
	public boolean isValid2() {
		return valid2;
	}
	
	public String getTipo_rendimento() {
		return this.tipo_rendimento;
	}


	public Page<ContratoInvestimentoDetalhadoDto> converter(Page<ContratoInvestimento> contratos) {
		return contratos.map(ContratoInvestimentoDetalhadoDto::new);
	}

}
