package br.com.infobtc.controller.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final String SENDER_EMAIL = "augusto.castro@sempreceub.com";
	private final String SENDER_PASSWORD = "GU@051316";

	public void send(String ...args) {
		try {
			StringBuilder msg = new StringBuilder();
			msg.append("<html><p>Olá, %s. Você acaba de receber um token para realizar o seu cadastro em nosso sistema. O código é: <strong>%s</strong></html>.");
			msg.append("<br>Faça o cadastro até às 00:00 do dia de hoje, pois token expira o nesse horário.");
			msg.append("<br><br>Para se cadastrar acesso agora: http://infobtcbr.com.br/cliente.</p>");
			
			String mensagem = String.format(msg.toString(), args[0], args[2]);
			
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(SENDER_EMAIL, SENDER_PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(SENDER_EMAIL);
			email.setSubject("Realize seu cadastro no InfoBTC");
			email.setHtmlMsg(mensagem);

			email.addTo(args[1]);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}