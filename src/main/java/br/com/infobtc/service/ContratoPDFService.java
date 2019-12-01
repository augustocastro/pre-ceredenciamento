package br.com.infobtc.service;

import static br.com.infobtc.helper.ArquivoHelper.lerArquivo;
import static br.com.infobtc.helper.DataHelper.retornaMes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.model.EstadoCivil;
import br.com.infobtc.model.Investidor;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.InvestidorPessoaJuridica;

@Service
public class ContratoPDFService {

	public File gerarPdf(Contrato contrato) throws IOException, DocumentException {
		OutputStream os = null;
		
		boolean isInvestimento = contrato instanceof ContratoInvestimento ? true : false;
		Map<String, String> dadosInvestidor = retornaDadosInvestidor(contrato, isInvestimento);
		LocalDate hoje = LocalDate.now();
		
		String nomeInvestidor = dadosInvestidor.get("nomeInvestidor");
		String naoSei1 = dadosInvestidor.get("naoSe1");
//		String naoSei2 = dadosInvestidor.get("naoSe2");
		String tipoDocumento = dadosInvestidor.get("tipoDocumento");
		String documento = dadosInvestidor.get("documento");
		String endereco = dadosInvestidor.get("endereco");
		String cep = dadosInvestidor.get("cep");

		String page1 = String.format(lerArquivo("contrato/page1.txt"), nomeInvestidor, naoSei1, tipoDocumento, 
				documento, endereco, cep, "10%");
		String page2 = lerArquivo("contrato/page2.txt");
		String page3 = lerArquivo("contrato/page3.txt");
		String page4 = String.format(lerArquivo("contrato/page4.txt"), hoje.getDayOfMonth(), retornaMes(hoje.getMonthValue()), 
				hoje.getYear(), nomeInvestidor, tipoDocumento, documento);

		try {
			final String[] paginas = new String[] { page1, page2, page3, page4 };

			final File outputFile = File.createTempFile("contrato", ".pdf");
			os = new FileOutputStream(outputFile);

			ITextRenderer renderer = new ITextRenderer();

			renderer.setDocumentFromString(paginas[0]);
			renderer.layout();
			renderer.createPDF(os, false);

			for (int i = 1; i < paginas.length; i++) {
				renderer.setDocumentFromString(paginas[i]);
				renderer.layout();
				renderer.writeNextDocument();
			}

			renderer.finishPDF();

			return outputFile;
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	private Map<String, String> retornaDadosInvestidor(Contrato contrato, boolean isInvestimento) {
		Map<String, String> dadosInvestidor = new HashMap<String, String>();
		
		String documento;
		String tipoDocumento;
		EstadoCivil naoSei1 = null;

		if (isInvestimento) {
			ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;

			if (contratoInvestimento.getInvestidor().getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
				naoSei1 = investidorPessoaFisica.getEstadoCivil();
			} else {
				InvestidorPessoaJuridica investidorPessoaJuridica = (InvestidorPessoaJuridica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaJuridica.getCnpj();
				tipoDocumento = "CNPJ";
			}

			dadosInvestidor.put("nomeInvestidor", contratoInvestimento.getInvestidor().getNome().toUpperCase());
			dadosInvestidor.put("tipoDocumento", tipoDocumento);
			dadosInvestidor.put("documento", documento);
			dadosInvestidor.put("endereco", contratoInvestimento.getInvestidor().getEndereco().getEndereco().toUpperCase());
			dadosInvestidor.put("cep", contratoInvestimento.getInvestidor().getEndereco().getCep());
			dadosInvestidor.put("naoSe1", naoSei1.toString());
//			dadosInvestidor.put("naoSe2", "Não Sei 2");
		} else {
			ContratoReinvestimento contratoReinvestimento = (ContratoReinvestimento) contrato;
			Investidor investidor = contratoReinvestimento.getInvestimento().getInvestidor();

			if (investidor.getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) investidor;
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
				naoSei1 = investidorPessoaFisica.getEstadoCivil();
			} else {
				InvestidorPessoaJuridica investidorPessoaJuridica = (InvestidorPessoaJuridica) investidor;
				documento = investidorPessoaJuridica.getCnpj();
				tipoDocumento = "CNPJ";
			}

			dadosInvestidor.put("nomeInvestidor", investidor.getNome().toUpperCase());
			dadosInvestidor.put("tipoDocumento", tipoDocumento);
			dadosInvestidor.put("documento", documento);
			dadosInvestidor.put("endereco", investidor.getEndereco().getEndereco().toUpperCase());
			dadosInvestidor.put("cep", investidor.getEndereco().getCep());
			dadosInvestidor.put("naoSe1", naoSei1.toString());
//			dadosInvestidor.put("naoSe2", "Não Sei 2");
		}

		return dadosInvestidor;
	}

}
