package br.com.infobtc.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final String SENDER_EMAIL = "infobtctest@gmail.com";
	private final String SENDER_PASSWORD = "infobtctest123";

	public void send(String ...args) {
		try {
			StringBuilder msg = new StringBuilder();
			msg.append("<html><head><meta charset=\"utf-8\"></head>");
			msg.append("<body><p>Olá, %s. Você acaba de receber um token para realizar o seu cadastro em nosso sistema. O código é: <strong>%s</strong>");
			msg.append("<br>Faça o cadastro até às 00:00 do dia de hoje, pois o token expira nesse horário.");
			msg.append("<br><br>Para se cadastrar acesse: <a href='http://infobtcbr.com.br/cliente'>http://infobtcbr.com.br/cliente</a></p></body></html>");
			
			String mensagem = String.format(msg.toString(), args[0], args[2]);
			
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(SENDER_EMAIL, SENDER_PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(SENDER_EMAIL);
			email.setSubject("Realize seu cadastro no InfoBTC");
			email.setHtmlMsg(mensagem);
			email.setContent(mensagem, "text/html; charset=UTF-8");
			email.addTo(args[1]);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}