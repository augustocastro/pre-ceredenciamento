package br.com.infobtc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

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
public class InvestimendoPDFService {

	public File gerarPdf(Contrato contrato) throws Docx4JException, IOException, DocumentException {
		OutputStream os = null;
		
		boolean isInvestimento = false;
		
		if (contrato instanceof ContratoInvestimento) {
			isInvestimento = true;
		}
		
		try {
			System.out.println(page4(contrato, isInvestimento));
			final String[] inputs = new String[] {
					page1(contrato, isInvestimento),
					page2(contrato, isInvestimento),
					page3(contrato, isInvestimento),
					page4(contrato, isInvestimento)
					
			};

			final File outputFile = File.createTempFile("FlyingSacuer.PDFRenderToMultiplePages", ".pdf");
			os = new FileOutputStream(outputFile);

			ITextRenderer renderer = new ITextRenderer();

			System.out.println(inputs[0]);
			renderer.setDocumentFromString(page1(contrato, isInvestimento));
			renderer.layout();
			renderer.createPDF(os, false);

			for (int i = 1; i < inputs.length; i++) {
				renderer.setDocumentFromString(inputs[i]);
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
	
	private String page1(Contrato contrato, boolean isInvestimento) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html><p style=\"text-align: center;margin-bottom:0;\"><strong>CONTRATO DE PRESTAÇÃO DE SERVIÇOS PARA INVESTIMENTO EM BITCOIN – MOEDA CRIPTOGRAFADA.</strong></p><br/>");
		html.append("<p style=\"text-align: justify;\">Pelo presente instrumento particular de prestação de serviços, de um lado doravante denominado Contratante <strong>%s</strong>, brasileiro, <strong>%s</strong>, <strong>%s</strong>, inscrito no %s sob o nº %s residente e domiciliado na <strong>%s</strong>, CEP: <strong>%s</strong> e de outro lado como Contratado GLAIDSON ACÁCIO DOS SANTOS, brasileiro, casado, Trader – profissional liberal cód. 11, ocupação principal – agente bolsa de valores câmbio e outros serviços financeiros cód. 353, portador da carteira de identidade RG nº 20.456.157-5 DETRAN/RJ, inscrito no CPF sob o nº 056.440.637-63, Escritório Matriz – Av. Júlia Kubitschek 16, Edifício. Premier Center, sala 316, Cabo Frio/RJ, CEP: 28.905-000, têm entre si justos e acordados o que segue: </p>");
		
		html.append("<strong>CLÁUSULA PRIMEIRA:</strong>");
		html.append("<p style=\"margin-bottom:0\">O Contratado deverá manter, enquantodurar este contrato, a sua condição de TRADER autônomo, com o respectivo registro regular na profissão, sob pena de se ocorrer a suspensão do exercício profissional ou cancelamento de seu registro, este contrato será considerado extinto.</p>");
		html.append("<p style=\"text-align: justify;margin-top:0;\"><strong>PARÁGRAFO ÚNICO</strong>: No caso de extinção do presente contrato, pelo motivo especificado na cláusula primeira, o Contratado deverá efetuar imediatamente a devolução do valor dado a título de investimento.</p>");
		
		html.append("<strong>CLÁUSULA SEGUNDA:</strong>");
		html.append("<p style=\"margin-bottom:0;\">2.1 O Contratado prestará aos Contratantes os seguintes serviços:</p>");
		html.append("<p style=\"text-align: justify;justify;margin:0;\">2.1.1 Aplicação de dinheiro brasileiro em mercado financeiro da moeda criptografada denominada BITCOIN.</p>");
		html.append("<p style=\"text-align: justify;justify;margin-top:0;\">2.1.2 Compreendendo um conjunto de operações de compra e venda nas plataformas Exchange BITSTAMP.NET, OKCOIN.COM, BINANCE.COM, BITMEX.COM e BITFINEX.COM à manutenção e formação de recursos monetários indispensáveis ao retorno do capital investido.</p>");
		
		html.append("<strong>CLÁUSULA TERCEIRA:</strong><br/>");
		html.append("<p style=\"margin-bottom:0\">3.1. Do Retorno Financeiro:</p>");
		html.append("<p style=\"text-align: justify;justify;margin-top:0;\">3.1.1 A título de retorno mensal fixo os Contratantes receberão o percentual liquido de %s sobre o valor investido (capital) de R$ XXXXXX (XXXXX reais), todo dia XX (XXXXX) do mês subsequente a aplicação financeira, por um prazo de 24 (vinte e quatro) meses, resgatando o capital investido ao término do prazo estipulado neste contrato.</p></html>");
		System.out.println(html.toString());
		
		if (isInvestimento) {
			ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;
			String documento = "";
			String tipoDocumento = "";
			
			if (contratoInvestimento.getInvestidor().getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
			} else {
				InvestidorPessoaJuridica investidorPessoaFisica = (InvestidorPessoaJuridica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCnpj();
				tipoDocumento = "CNPJ";
			}
			
			
			return String.format(html.toString(), contratoInvestimento.getInvestidor().getNome().toUpperCase(), "XXXXX", "XXXXX", tipoDocumento, documento, contratoInvestimento.getInvestidor().getEndereco().getEndereco(), contratoInvestimento.getInvestidor().getEndereco().getCep(), "10%");

		} else {
			ContratoReinvestimento contratoReinvestimento = (ContratoReinvestimento) contrato;
			String documento = "";
			String tipoDocumento = "";
			
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
			
			return String.format(html.toString(),investidor.getNome(), "NS", "NS", tipoDocumento, documento, investidor.getEndereco().getEndereco(), investidor.getEndereco().getCep(), "10%");

		}
		
	}
	
	private String page2(Contrato contrato, boolean isInvestimento) {
		StringBuilder html = new StringBuilder();
		html.append("<html><p style=\"text-align: justify;margin-bottom:0;\">3.1.2 O Contratado receberá mensalmente, a título de contrapartida por seus serviços financeiros o valor que ultrapasse o percentual liquido auferido pelos Contratantes, enquanto durar o presente contrato.</p>");
		html.append("<p style=\"margin-top:0;\"><strong>PARÁGRAFO ÚNICO</strong>: Cumpre ressaltar, que é de inteira responsabilidade do INVESTIDOR a origem do capital investido, não sendo permitido o investimento de origem ILICÍCITA, sob pena de rescisão do presente contrato, e a devolução do capital investido.</p>");
		
		html.append("<strong>CLÁUSULA QUARTA: </strong> <br/>"); 
		html.append("<p style=\"text-align: justify;margin-bottom:0;\">4.1 São obrigações do Contratado:</p>");
		html.append("<p style=\"text-align: justify;margin:0\">4.1.1 Efetuar o pagamento do retorno do investimento, consoante o disposto na cláusula terceira deste instrumento. </p>"); 
		html.append("<p style=\"text-align: justify;margin:0\">4.1.2 Expedir mensalmente para os Contratantes as cópias dos demonstrativos financeiros efetivamente realizados. </p>");
		html.append("<p style=\"margin:0;\">4.1.3 Prestar todo o tipo de informação relevante aos Contratantes no que diz respeito ao investimento em Bitcoin. </p>");
		html.append("<p style=\"text-align: justify;margin:0\">4.1.4 Todas as comunicações do Contratado para com os Contratantes devem ser feitas por escrito, que inclui serviços de e-mail e outros serviços de mensagens eletrônicas. </p>"); 
		html.append("<p style=\"text-align: justify;margin-top:0\">4.1.5 Manter sigilo sobre as atividades dos Contratantes, a menos, que os próprios autorizem a divulgação das informações.</p>"); 
		
		html.append("<strong>CLÁUSULA QUINTA: </strong> <br/>"); 
		html.append("<p style=\"margin-bottom:0;\">5.1 São obrigações dos Contratantes: </p>"); 
		html.append("<p style=\"margin:0;\">5.1.1 Cumprir integralmente as disposições deste instrumento contratual.</p>"); 
		html.append("<p style=\"margin:0;\">5.1.2 Seguir as instruções do Contratado, no que diz respeito aos serviços prestados. </p>"); 
		html.append("<p style=\"text-align: justify;margin-top:0\">5.1.3 Fornecer ao Contratado, regularmente e quando solicitado, informações que por ventura sejam relevantes ao bom andamento dos investimentos.</p>");
		
		html.append("<strong>CLÁUSULA SEXTA: </strong> <br/>"); 
		html.append("<p style=\"text-align: justify;margin-bottom:0;\">6.1 Constituem motivos justos para rescisão deste contrato, pelos Contratantes: Desídia do Contratado no cumprimento das obrigações assumidas para com os Contratantes. </p>"); 
		html.append("<p  style=\"text-align: justify;margin:0\">6.1.1 Prática de atos, por parte do Contratado, que importem em descrédito comercial dos Contratantes perante terceiros. </p>"); 
		html.append("<p style=\"margin:0;\">6.1.2 A falta de cumprimento, pelo Contratado, de quaisquer obrigações inerentes a este contrato</p></html>");
		
		System.out.println(html);
		
		return html.toString();
	}
	
	
	private String page3(Contrato contrato, boolean isInvestimento) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html><strong>CLÁUSULA SÉTIMA:</strong>");
		html.append("<p style=\"margin-bottom:0;text-align: justify;\">7.1 Constituem motivos justos para rescisão deste contrato pelo Contratado:</p>");
		html.append("<p style=\"margin:0;\">7.1.1 Solicitação por parte dos Contratantes de exercício de atividades não previstas no presente contrato.</p>"); 
		html.append("<p style=\"margin:0;\">7.1.2 A falta de cumprimento, pelos Contratantes, de quaisquer obrigações inerentes a este contrato.</p>"); 
		html.append("<p style=\"margin:0;\">7.1.3 A falta de remuneração, conforme previsto na cláusula terceira deste contrato.</p>"); 
		html.append("<p style=\"margin:0;text-align: justify;\">7.1.4 Por motivos de caso fortuito ou de força maior objetivando, principalmente, que os Contratantes não incorram em prejuízos.</p>");
		html.append("<p style=\"margin-top:0;text-align: justify;\"><strong>PARÁGRAFO ÚNICO:</strong> Em caso de rescisão unilateral por parte do Contratado, o mesmo deverá notificar os Contratantes, com o prazo mínimo de 90 (noventa) dias.</p>");
		
		html.append("<strong>CLÁUSULA OITAVA:</strong>");
		html.append("<p style=\"text-align: justify;margin-bottom:0;\">A duração deste contrato é pelo prazo de 24 (vinte e quatro) meses. Todavia, o mesmo poderá ser renovado, de comum acordo, uma única vez por mais 12 (doze) meses.</p>"); 
		html.append("<p style=\"text-align: justify;margin:0;\">8.1 É obrigação do CONTRATANTE informar, caso necessário, 30 dias antes do término do contrato para resgatar o capital.</p>"); 
		html.append("<p style=\"text-align: justify;margin-top:0;\"><strong>Parágrafo único:</strong> Com o término do presente contrato, o valor oferecido a título de investimento será restituído, imediatamente, aos Contratantes pelo Contratado.</p>"); 
		
		html.append("<strong>CLÁUSULA NONA:</strong>"); 
		html.append("<p style=\"text-align: justify;\">Como garantia do pagamento ao final, no valor dado a título de investimento, o Contratado emitirá uma nota promissória em favor dos Contratantes na data de assinatura deste	instrumento contratual. E, ocorrendo o pagamento do valor em referência, no prazo fixado neste contrato, o título executivo será resgatado por seu subscritor.</p>");
		
		html.append("<strong>CLÁUSULA DÉCIMA:</strong>");
		html.append("<p style=\"text-align: justify;\">A rescisão do presente contrato, não extinguirá os direitos e obrigações que as partes tenham entre si e para com terceiros.</p>"); 
		
		html.append("<strong>CLÁUSULA DÉCIMA PRIMEIRA:</strong>"); 
		html.append("<p style=\"text-align: justify;\">As partes elegem o foro de Brasília/DF, para dirimir todas e quaisquer dúvidas ou questões oriundas do presente contrato, renunciando as partes a qualquer outro, por mais especial e privilegiado que seja.</p></html>");
		
		return html.toString();
	}
	
	private String page4(Contrato contrato, boolean isInvestimento) {

		LocalDate hoje =  LocalDate.now();
		
		StringBuilder html = new StringBuilder();
		html.append("<html><div style=\"position:absolute;top:1.72in;\">");
		html.append("<span style=\"text-align: justify;font-style:normal;font-weight:normal;font-size:12pt;font-family:Arial;color:#000000\"> E, por estarem assim, justos e contratados, assinam o presente contrato na presença de duas testemunhas, para que se produzam os devidos efeitos legais.</span>");
		html.append(String.format("<div style=\"position:absolute;top:1.13in;left:4.70in;width:3.76in;line-height:0.17in;\"><span style=\"font-style:normal;font-weight:normal;font-size:12pt;font-family:Arial;color:#000000\">Brasília, %s de %s de %s</span></div></div>.", hoje.getDayOfMonth(), retornaMes(hoje.getMonthValue()), hoje.getYear()));
		
		html.append("<div style=\"position:absolute;top:5.57in;left:1in;width:5.05in;line-height:0.16in;\"><span>___________________________________________________________</span></div>");
		
		html.append("<div style=\"position:absolute;top:5.79in;left:1.9in;width:4.04in;line-height:0.16in;\"><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000\">CONTRATADO:<strong> GLAIDSON ACÁCIO DOS SANTOS</strong></span></div>");
		
		html.append("<div style=\"position:absolute;top:5.98in;left:2.5in;width:1.84in;line-height:0.16in;\"><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000;\">CPF nº: 056.440.637-63</span></div>");
		
		html.append("<div style=\"position:absolute;top:7.20in;left:1in;width:5.14in;line-height:0.16in;\"><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000\">_________________________________________________________________</span></div>");
		
		html.append("<div style=\"position:absolute;top:7.39in;left:1.9in;width:3.17in;line-height:0.16in;\"><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000\">CONTRATANTE: <strong>%s</strong> </span></div>");
		
		html.append("<div style=\"position:absolute;top:7.59in;left:2.5in;width:1.84in;line-height:0.16in;\"><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000\">%s nº: %s</span><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:Arial;color:#000000\"> </span></div>");
	
		html.append("<div style=\"position:absolute;top:8.97in;left:0.86in;width:7.67in;line-height:0.17in;\"><span style=\"font-style:normal;font-weight:normal;font-size:12pt;font-family:Arial;color:#000000\">Testemunha(1): ____________________ Testemunha (2): ____________________ </span><span style=\"font-style:normal;font-weight:normal;font-size:12pt;font-family:Arial;color:#000000\"> </span></div>");
		
		html.append("<div style=\"position:absolute;top:9.39in;left:0.86in;width:7.29in;line-height:0.21in;\"><span	style=\"font-style:normal;font-weight:normal;font-size:12pt;font-family:Arial;color:#000000\">CPF: _____________________________ CPF: _____________________________</span><span style=\"font-style:normal;font-weight:normal;font-size:11pt;font-family:ABCDEE+Calibri;color:#000000\"></span></div></html>");
	
		
		if (isInvestimento) {
			ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;
			String documento = "";
			String tipoDocumento = "";

			if (contratoInvestimento.getInvestidor().getTipo().equals("pessoa_fisica")) {
				InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCpf();
				tipoDocumento = "CPF";
			} else {
				InvestidorPessoaJuridica investidorPessoaFisica = (InvestidorPessoaJuridica) contratoInvestimento.getInvestidor();
				documento = investidorPessoaFisica.getCnpj();
				tipoDocumento = "CNPJ";
			}
			
			return String.format(html.toString(), contratoInvestimento.getInvestidor().getNome().toUpperCase(), tipoDocumento, documento);

		} else {
			ContratoReinvestimento contratoReinvestimento = (ContratoReinvestimento) contrato;
			String documento = "";
			String tipoDocumento = "";
			
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
			
			return String.format(html.toString(), investidor.getNome().toUpperCase(), tipoDocumento, documento);
		}
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
