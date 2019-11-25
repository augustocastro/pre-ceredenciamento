package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Parcela;
import br.com.infobtc.model.TipoRecebedor;

public class ParcelaDto {

	private String data;
	private int numero_parcela;
	private boolean repassado;

	public ParcelaDto() {

	}

	public ParcelaDto(Parcela parcela) {
		this.data = parcela.getData() != null ? parcela.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyy")): null;
		this.numero_parcela = parcela.getParcela();
		this.repassado = parcela.getRepasses().stream()
				.filter(repasse -> repasse.getTipoRecebedor().toString().equals(TipoRecebedor.INVESTIDOR.toString()))
				.collect(Collectors.toList())
				.size() > 0;
	}

	public String getData() {
		return data;
	}

	public int getNumero_parcela() {
		return numero_parcela;
	}

	public boolean isRepassado() {
		return repassado;
	}

	public List<ParcelaDto> converter(List<Parcela> parcelas) {
		return parcelas.stream().map(ParcelaDto::new).collect(Collectors.toList());
	}

}
