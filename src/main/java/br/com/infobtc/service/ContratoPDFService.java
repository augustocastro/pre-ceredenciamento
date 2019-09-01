package br.com.infobtc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.model.Investidor;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.InvestidorPessoaJuridica;

@Service
public class ContratoPDFService {

	private final String CAMINHO_PASTA_RESOURCE = "src/main/resources/contrato";

	public File gerarPdf(Contrato contrato) throws Docx4JException, IOException, DocumentException {
		OutputStream os = null;

		boolean isInvestimento = contrato instanceof ContratoInvestimento ? true : false;
		Map<String, String> dadosInvestidor = retornaDadosInvestidor(contrato, isInvestimento);
		LocalDate hoje = LocalDate.now();

		String page1 = String.format(lerArquivo("page1.txt"), 
				dadosInvestidor.get("nomeInvestidor"),
				dadosInvestidor.get("NAO_SEI1"), 
				dadosInvestidor.get("NAO_SEI1"), 
				dadosInvestidor.get("tipoDocumento"),
				dadosInvestidor.get("documento"), 
				dadosInvestidor.get("endereco"), 
				dadosInvestidor.get("cep"), 
				"10%");

		String page2 = lerArquivo("page2.txt");
		String page3 = lerArquivo("page3.txt");

		String page4 = String.format(lerArquivo("page4.txt"), 
				hoje.getDayOfMonth(), 
				retornaMes(hoje.getMonthValue()),
				hoje.getYear(), 
				dadosInvestidor.get("nomeInvestidor"), 
				dadosInvestidor.get("tipoDocumento"),
				dadosInvestidor.get("documento"));

		try {
			final String[] paginas = new String[] { page1, page2, page3, page4 };

			final File outputFile = File.createTempFile("FlyingSacuer.PDFRenderToMultiplePages", ".pdf");
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
				try {
					os.close();
				} catch (IOException e) {

				}
			}
		}
	}

	private String lerArquivo(String nomeArquivo) throws IOException {
		String file = CAMINHO_PASTA_RESOURCE + "/" + nomeArquivo;
		StringBuilder conteudo = new StringBuilder();
		FileReader ler = new FileReader(file);
		BufferedReader reader = new BufferedReader(ler);
		String linha;

		while ((linha = reader.readLine()) != null) {
			conteudo.append(linha);

		}
		
		if (reader != null) {
			reader.close();
		}

		return conteudo.toString();
	}

	private Map<String, String> retornaDadosInvestidor(Contrato contrato, boolean isInvestimento) {
		Map<String, String> dadosInvestidor = new HashMap<String, String>();
		String documento;
		String tipoDocumento;
		
		if (isInvestimento) {
			ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;

			if (contratoInvestimento.getInvestidor().getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) contratoInvestimento
						.getInvestidor();
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
			} else {
				InvestidorPessoaJuridica investidorPessoaFisica = (InvestidorPessoaJuridica) contratoInvestimento
						.getInvestidor();
				documento = investidorPessoaFisica.getCnpj();
				tipoDocumento = "CNPJ";
			}

			dadosInvestidor.put("nomeInvestidor", contratoInvestimento.getInvestidor().getNome().toUpperCase());
			dadosInvestidor.put("tipoDocumento", tipoDocumento);
			dadosInvestidor.put("documento", documento);
			dadosInvestidor.put("endereco", contratoInvestimento.getInvestidor().getEndereco().getEndereco().toUpperCase());
			dadosInvestidor.put("cep", contratoInvestimento.getInvestidor().getEndereco().getCep());
			dadosInvestidor.put("NAO_SEI1", "NAO_SEI1");
			dadosInvestidor.put("NAO_SEI2", "NAO_SEI2");
		} else {
			ContratoReinvestimento contratoReinvestimento = (ContratoReinvestimento) contrato;
			Investidor investidor = contratoReinvestimento.getInvestimento().getInvestidor();

			if (investidor.getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) investidor;
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
			} else {
				InvestidorPessoaJuridica investidorPessoaFisica = (InvestidorPessoaJuridica) investidor;
				documento = investidorPessoaFisica.getCnpj();
				tipoDocumento = "CNPJ";
			}

			dadosInvestidor.put("nomeInvestidor", investidor.getNome().toUpperCase());
			dadosInvestidor.put("tipoDocumento", tipoDocumento);
			dadosInvestidor.put("documento", documento);
			dadosInvestidor.put("endereco", investidor.getEndereco().getEndereco().toUpperCase());
			dadosInvestidor.put("cep", investidor.getEndereco().getCep());
			dadosInvestidor.put("NAO_SEI1", "NAO_SEI1");
			dadosInvestidor.put("NAO_SEI2", "NAO_SEI2");
		}

		return dadosInvestidor;
	}

	private String retornaMes(int mes) {
		switch (mes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return "";
		}
	}

}
