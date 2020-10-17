package br.com.precredenciamento.controller.service;

import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.precredenciamento.controller.form.LoginUsuarioExternoForm;
import br.com.precredenciamento.controller.form.RecuperarSenhaUsuarioExternoForm;
import br.com.precredenciamento.model.Codigo;
import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.repository.UsuarioExternoRepository;
import br.com.precredenciamento.service.EmailService;
import br.com.precredenciamento.util.CodeUtil;
import javassist.NotFoundException;

@Service
public class AutenticacaoUsuarioExternoService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private CodigoService codigoRecuperacaoSenhaService;

	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;

	public UsuarioExterno autenticar(LoginUsuarioExternoForm form) throws NotFoundException, AuthenticationException, javax.naming.AuthenticationException {
		UsuarioExterno usuario = usuarioExternoRepository.findByCpf(form.getCpf());

		if (usuario != null) {
			if (usuario.getSenha().equals(form.getSenha())) {
				return usuario;
			} else {
				throw new AuthenticationException("Senha incorreta!");
			}
		} else {
			throw new NotFoundException("Usuário não encontrado!");
		}
	}

	public Codigo enviarEmailRecuperacaoSenha(RecuperarSenhaUsuarioExternoForm form, String urlOrigin) {
		String codigo = CodeUtil.gerarCodigo();

		UsuarioExterno usuario = usuarioExternoRepository.findByCpf(form.getCpf());
		
		Codigo codigoRecuperacaoSenha = codigoRecuperacaoSenhaService.salvar(new Codigo(codigo, form.getCpf()));

		String assunto = "SESC - Recuperação da Senha";
		String destinatario = form.getEmail();

		StringBuilder mensagem = new StringBuilder();
//		mensagem.append("<img src=\"logo.png\" height=\"100\" width=\"100\">");
		mensagem.append("Ola! <strong><user></strong> Segue o Link abaixo para Trocar sua senha<br><br>".replace("<user>", usuario.getNomeCompleto()));
		mensagem.append("<a href='<origin>/trocar-senha/?codigo=<code>'>http://localhost:4200/trocar-senha/?codigo=<code></a>".replaceAll("<code>", codigo).replace("<origin>", urlOrigin));

		emailService.send(mensagem.toString(), assunto, destinatario);

		return codigoRecuperacaoSenha;
	}
}
