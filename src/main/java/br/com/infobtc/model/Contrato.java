package br.com.infobtc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dtInicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dtTermino;
	
	private Integer quantidadeMeses;
	
	private BigDecimal valor;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Banco banco;
	
	private boolean valid1;
	private boolean valid2;
	
	public Long getId() {
		return id;
	}

	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public LocalDate getDtTermino() {
		return dtTermino;
	}

	public Integer getQuantidadeMeses() {
		return quantidadeMeses;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	public void setDtTermino(LocalDate dtTermino) {
		this.dtTermino = dtTermino;
	}

	public void setQuantidadeMeses(Integer quantidadeMeses) {
		this.quantidadeMeses = quantidadeMeses;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public boolean isValid1() {
		return valid1;
	}
	
	public void setValid1(boolean valid1) {
		this.valid1 = valid1;
	}
	
	public boolean isValid2() {
		return valid2;
	}
	
	public void setValid2(boolean valid2) {
		this.valid2 = valid2;
	}

	public abstract Object criaDto(Contrato contrato);
}
