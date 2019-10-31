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
	private String status_contrato;
	private String status_financeiro;
	private String tipo_rendimento;
	private String justificativa_reprovacao;
	private boolean repassado;
	private InvestidorDto investidor;
	private ConsultorDto consultor;
	private BancoDto banco;
	private String banco_recebimento_escritorio;
	private List<ContratoReinvestimentoDto> reinvestimentos = new ArrayList<ContratoReinvestimentoDto>();
	private List<String> arquivos_url = new ArrayList<String>();
	
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
		this.status_contrato = contratoInvestimento.getStatusContrato().toString();
		this.status_financeiro = contratoInvestimento.getStatusFinanceiro().toString();
		this.tipo_rendimento = contratoInvestimento.getTipoRendimento();
		this.arquivos_url = contratoInvestimento.getArquivosUrl();
		this.justificativa_reprovacao = contratoInvestimento.getJustificativaReprovacao();
		this.repassado = contratoInvestimento.isRepassado();
		this.banco_recebimento_escritorio = contratoInvestimento.getBancoRecebimentoEscritorio();
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

	public boolean isRepassado() {
		return repassado;
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

	public String getBanco_recebimento_escritorio() {
		return banco_recebimento_escritorio;
	}
	
	public List<ContratoReinvestimentoDto> getReinvestimentos() {
		return reinvestimentos;
	}
	
	public String getStatus_contrato() {
		return status_contrato;
	}
	
	public String getStatus_financeiro() {
		return status_financeiro;
	}
	
	public String getTipo_rendimento() {
		return this.tipo_rendimento;
	}

	public String getJustificativa_reprovacao() {
		return justificativa_reprovacao;
	}
	
	public List<String> getArquivos_Url() {
		return arquivos_url;
	}

	public Page<ContratoInvestimentoDetalhadoDto> converter(Page<ContratoInvestimento> contratos) {
		return contratos.map(ContratoInvestimentoDetalhadoDto::new);
	}

}
