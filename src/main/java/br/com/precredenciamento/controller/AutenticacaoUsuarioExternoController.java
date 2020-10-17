package br.com.precredenciamento.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.precredenciamento.controller.dto.ErroDto;
import br.com.precredenciamento.controller.dto.UsuarioExternoDto;
import br.com.precredenciamento.controller.form.LoginUsuarioExternoForm;
import br.com.precredenciamento.controller.form.NovaSenhaUsuarioExternoForm;
import br.com.precredenciamento.controller.form.RecuperarSenhaUsuarioExternoForm;
import br.com.precredenciamento.controller.service.AutenticacaoUsuarioExternoService;
import br.com.precredenciamento.controller.service.CodigoService;
import br.com.precredenciamento.controller.service.UsuarioExternoService;
import br.com.precredenciamento.model.Codigo;
import br.com.precredenciamento.model.UsuarioExterno;
import javassist.NotFoundException;

@RestController
@RequestMapping("/auth/usuario-externo")
public class AutenticacaoUsuarioExternoController {
	
	@Autowired
	private AutenticacaoUsuarioExternoService autenticacaoUsuarioExternoService;
	
	@Autowired
	private CodigoService codigoService;
	
	@Autowired
	private UsuarioExternoService externoService;

	@PostMapping
    public ResponseEntity<?> autenticar(@Valid @RequestBody LoginUsuarioExternoForm form) {
		try {
			UsuarioExterno usuario = autenticacaoUsuarioExternoService.autenticar(form);
			UsuarioExternoDto dto = new UsuarioExternoDto().converter(usuario);
			return ResponseEntity.status(200).body(dto);
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new ErroDto(e.getMessage(), true, "/login-ext"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(401).body(new ErroDto(e.getMessage(), true, "/login-ext"));
		}
		
    }
	
	@PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@Valid @RequestBody RecuperarSenhaUsuarioExternoForm form, HttpServletRequest request) {
		try {
			String urlOrigem = request.getHeader("origin");
			Codigo codigoRecuperacaoSenha = autenticacaoUsuarioExternoService.enviarEmailRecuperacaoSenha(form, urlOrigem);
			return ResponseEntity.status(200).body(codigoRecuperacaoSenha);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro ao enviar e-email."));
		}
    }
	
	@GetMapping("recuperar-senha/validar-codigo/")
    public ResponseEntity<?> validarCodigo(@NotBlank @RequestParam(value = "codigo") String codigo) {
		try {
			codigoService.validarCodigo(codigo);
			return ResponseEntity.status(200).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new ErroDto("Código inválido.", false));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro ao recuperar código.", false));
		}
    }
	
	@PostMapping("recuperar-senha/nova-senha/")
	@Transactional
    public ResponseEntity<?> trocarSenha(@Valid @RequestBody NovaSenhaUsuarioExternoForm form) {
		try {
			Codigo codigo = codigoService.validarCodigo(form.getCodigo());
			externoService.alterarSenha(form, codigo);
			return ResponseEntity.status(200).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new ErroDto(e.getMessage()));
		}catch (IllegalArgumentException e) {
			return ResponseEntity.status(500).body(new ErroDto(e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto());
		}
    }
}
