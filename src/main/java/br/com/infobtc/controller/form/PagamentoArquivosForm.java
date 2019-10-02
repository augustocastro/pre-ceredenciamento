package br.com.infobtc.controller.form;

import org.springframework.web.multipart.MultipartFile;

public class PagamentoArquivosForm {

	private String pagamento;
	private MultipartFile[] arquivos;

	public String getPagamento() {
		return pagamento;
	}

	public MultipartFile[] getArquivos() {
		return arquivos;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public void setArquivos(MultipartFile[] arquivos) {
		this.arquivos = arquivos;
	}
}
