package br.com.infobtc.service;

import static br.com.infobtc.helper.ArquivoHelper.lerArquivo;
import static br.com.infobtc.helper.DataHelper.retornaMes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

	public File gerarPdf(Contrato contrato) throws IOException, DocumentException {
		final File outputFile = File.createTempFile("contrato", ".pdf");

		try (OutputStream os = new FileOutputStream(outputFile)){
			final String[] paginas = montarPdf(contrato);

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
		}
	}
	
	private String[] montarPdf(Contrato contrato) throws IOException {
		DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00");
		CurrencyWriter cw = new CurrencyWriter();
		
		Map<String, String> dadosInvestidor = retornaDadosInvestidor(contrato);
		LocalDate hoje = LocalDate.now();

		int diaContrato = contrato.getDtInicio().getDayOfMonth();
		int quantidadeMeses = contrato.getQuantidadeMeses();
		
		String nomeInvestidor = dadosInvestidor.get("nomeInvestidor");
		String estadoCivil = dadosInvestidor.get("estadoCivil");
		String regimeBens = dadosInvestidor.get("regimeBens");
		String tipoDocumento = dadosInvestidor.get("tipoDocumento");
		String documento = dadosInvestidor.get("documento");
		String endereco = dadosInvestidor.get("endereco");
		String cep = dadosInvestidor.get("cep");
		
		String nomeConsultor = contrato.getConsultor().getNome();
		String documentoConsultor = "000.000.000-00";
		
		String valorContratoFormatado = decimal.format(contrato.getValor());
		String valorContratoPorextenso = cw.write(contrato.getValor());
		String diaContratoPorExtenso = cw.write(new BigDecimal(contrato.getDtInicio().getDayOfMonth()));
		String quantidadeMesesPorExtenso = cw.write(new BigDecimal(contrato.getQuantidadeMeses()));

		diaContratoPorExtenso = diaContratoPorExtenso.replace("reais", "").replace("real", "").trim();
		quantidadeMesesPorExtenso = quantidadeMesesPorExtenso.replace("reais", "").replace("real", "").trim();

		Object paramsPage1[] = new Object[] { 
				nomeInvestidor, estadoCivil, regimeBens, tipoDocumento, 
				documento, endereco, cep, "10%", valorContratoFormatado, valorContratoPorextenso, 
				diaContrato, diaContratoPorExtenso, quantidadeMeses, quantidadeMesesPorExtenso 
		};
		
		Object paramsPage4[] = new Object[] { hoje.getDayOfMonth(),
				retornaMes(hoje.getMonthValue()), hoje.getYear(), nomeInvestidor, 
				tipoDocumento, documento, nomeConsultor, documentoConsultor 
		};

		String page1 = String.format(lerArquivo("contrato/page1.txt"), paramsPage1);
		String page2 = lerArquivo("contrato/page2.txt");
		String page3 = lerArquivo("contrato/page3.txt");
		String page4 = String.format(lerArquivo("contrato/page4.txt"), paramsPage4);
		
		return new String[] { page1, page2, page3, page4 };
	}

	private Map<String, String> retornaDadosInvestidor(Contrato contrato) {
		boolean isInvestimento = contrato instanceof ContratoInvestimento ? true : false;

		Map<String, String> dadosInvestidor = new HashMap<String, String>();

		String documento;
		String tipoDocumento;

		if (isInvestimento) {
			ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;

			if (contratoInvestimento.getInvestidor().getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
				dadosInvestidor.put("estadoCivil", investidorPessoaFisica.getEstadoCivil().toString());
				dadosInvestidor.put("regimeBens", investidorPessoaFisica.getRegime_bens());

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
		} else {
			ContratoReinvestimento contratoReinvestimento = (ContratoReinvestimento) contrato;
			Investidor investidor = contratoReinvestimento.getInvestimento().getInvestidor();

			if (investidor.getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) investidor;
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
				dadosInvestidor.put("estadoCivil", investidorPessoaFisica.getEstadoCivil().toString());
				dadosInvestidor.put("regimeBens", investidorPessoaFisica.getRegime_bens());
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
		}

		return dadosInvestidor;
	}

}
