package br.com.precredenciamento.controller.service;

import java.io.IOException;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.precredenciamento.controller.dto.UsuarioExternoListagemDto;
import br.com.precredenciamento.controller.form.AtualizarUsuarioExternoForm;
import br.com.precredenciamento.controller.form.AtualizarUsuarioExternoFormData;
import br.com.precredenciamento.controller.form.CadastrarUsuarioExternoForm;
import br.com.precredenciamento.controller.form.EnderecoForm;
import br.com.precredenciamento.controller.form.NovaSenhaUsuarioExternoForm;
import br.com.precredenciamento.model.Arquivo;
import br.com.precredenciamento.model.Codigo;
import br.com.precredenciamento.model.Endereco;
import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.repository.EnderecoRepository;
import br.com.precredenciamento.repository.UsuarioExternoRepository;
import br.com.precredenciamento.validacao.ValidacaoException;
import javassist.NotFoundException;

@Service
public class UsuarioExternoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;
	
	@Autowired
	private CodigoService codigoService;
	
	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private Validator validator;
	
	public UsuarioExterno atualizar(AtualizarUsuarioExternoFormData formData) throws JsonParseException, JsonMappingException, IOException, ValidacaoException {
		AtualizarUsuarioExternoForm form = new ObjectMapper().readValue(formData.getUsuario(), AtualizarUsuarioExternoForm.class);
		
		UsuarioExterno usuario = this.usuarioExternoRepository.findById(form.id).get();
		Endereco endereco = new Endereco();
		usuario.setEndereco(endereco);
		EnderecoForm enderecoForm = form.endereco;

		enderecoForm.setarPropriedades(endereco, validator);
		form.setarPropriedades(usuario, validator);

		enderecoRepository.save(endereco);
		usuarioExternoRepository.save(usuario);
		
		if (formData.getFotoPerfil() != null) {
			if (usuario.getFotoPerfil() != null) {				
				String idFotoPerfilAntiga = usuario.getFotoPerfil().getId();
				arquivoService.removerPorId(idFotoPerfilAntiga);
			}
			Arquivo fotoPerfil = arquivoService.salvar(formData.getFotoPerfil());
			usuario.setFotoPerfil(fotoPerfil);
		}
	
		usuarioExternoRepository.save(usuario);

		
		return usuario;
	}
	
	public UsuarioExterno salvar(CadastrarUsuarioExternoForm form) {
		UsuarioExterno usuario = new UsuarioExterno();
		form.setarPropriedades(usuario);
		usuarioExternoRepository.save(usuario);
		return usuario;
	}
	
	public Page<UsuarioExternoListagemDto> buscarTodos(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		return new UsuarioExternoListagemDto().converter(usuarioExternoRepository.findAll(paginacao));
	}
	
	public UsuarioExterno buscarPorCpf(String cpf) throws NotFoundException {
		UsuarioExterno usuario = this.usuarioExternoRepository.findByCpf(cpf);
		if (usuario != null) {
			return usuario;
		}
		throw new NotFoundException("Usuário não encontrado");
	}
	
	public UsuarioExterno buscarPorCpfAndId(String cpf, Long id) throws NotFoundException {
		UsuarioExterno usuario = this.usuarioExternoRepository.findByCpfAndId(cpf, id);
		if (usuario != null) {
			return usuario;
		}
		throw new NotFoundException("Usuário não encontrado");
	}
	
	public void alterarSenha(NovaSenhaUsuarioExternoForm form, Codigo codigo) throws NotFoundException {
		UsuarioExterno usuario = this.buscarPorCpf(codigo.getCpf());
		if (usuario != null) {
			if (form.getNovaSenha().equals(form.getNovaSenha())) {
				usuario.setSenha(form.getNovaSenha());
				codigoService.exluir(codigo);
			} else {
				throw new IllegalArgumentException("As senhas não batem");
			}
		} else {			
			throw new NotFoundException("Usuário não encontrado");
		}
	}
}
