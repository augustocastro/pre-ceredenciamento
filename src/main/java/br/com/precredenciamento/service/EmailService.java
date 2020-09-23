package br.com.precredenciamento.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final String SENDER_EMAIL = "sistema.precredenciamento@gmail.com";
	private final String SENDER_PASSWORD = "precrdenciamento@123";

	public void send(String ...args) {
		try {			
			String mensagem = String.format(args[0]);
			
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(SENDER_EMAIL, SENDER_PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(SENDER_EMAIL);
			email.setSubject(args[1]);
			email.setHtmlMsg(mensagem);
			email.setContent(mensagem, "text/html; charset=UTF-8");
			email.addTo(args[2]);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}