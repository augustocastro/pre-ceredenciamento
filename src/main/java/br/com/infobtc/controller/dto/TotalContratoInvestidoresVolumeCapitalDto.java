package br.com.infobtc.controller.dto;

import java.math.BigDecimal;

public class TotalContratoInvestidoresVolumeCapitalDto {

	private long total_contratos;
	private long total_investidores;
	private double volume_capital;

	public TotalContratoInvestidoresVolumeCapitalDto(long total_contratos, long total_investidores, BigDecimal volume_capital) {
		this.total_contratos = total_contratos;
		this.total_investidores = total_investidores;
		this.volume_capital = volume_capital != null ? volume_capital.doubleValue() : 0;
	}

	public long getTotal_contratos() {
		return total_contratos;
	}

	public long getTotal_investidores() {
		return total_investidores;
	}

	public double getVolume_capital() {
		return volume_capital;
	}

}
