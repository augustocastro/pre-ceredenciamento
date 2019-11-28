package br.com.infobtc.controller.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.infobtc.model.TipoRecebedor;
import br.com.infobtc.model.TipoRepasse;

public class RepasseEmLoteForm {

	private String observacao;
	private MultipartFile anexo;
	private List<Long> parcelas_id;
	private TipoRepasse tipo_repasse;
	private TipoRecebedor tipo_recebedor;
	private String data;

	public String getObservacao() {
		return observacao;
	}

	public MultipartFile getAnexo() {
		return anexo;
	}

	public List<Long> getParcelas_id() {
		return parcelas_id;
	}

	public TipoRepasse getTipo_repasse() {
		return tipo_repasse;
	}

	public String getData() {
		return data;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setAnexo(MultipartFile anexo) {
		this.anexo = anexo;
	}

	public void setParcelas_id(List<Long> parcelas_id) {
		this.parcelas_id = parcelas_id;
	}


	public void setTipo_repasse(TipoRepasse tipo_repasse) {
		this.tipo_repasse = tipo_repasse;
	}

	public TipoRecebedor getTipo_recebedor() {
		return tipo_recebedor;
	}
	
	public void setTipo_recebedor(TipoRecebedor tipo_recebedor) {
		this.tipo_recebedor = tipo_recebedor;
	}
	
	public void setData(String data) {
		this.data = data;
	}

}
