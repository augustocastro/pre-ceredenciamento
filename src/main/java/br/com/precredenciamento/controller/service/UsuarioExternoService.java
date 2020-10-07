package br.com.precredenciamento.controller.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.precredenciamento.controller.dto.UsuarioExternoListagemDto;
import br.com.precredenciamento.controller.form.AtualizarUsuarioExternoForm;
import br.com.precredenciamento.controller.form.AtualizarUsuarioExternoFormData;
import br.com.precredenciamento.controller.form.CadastrarUsuarioExternoForm;
import br.com.precredenciamento.controller.form.EnderecoForm;
import br.com.precredenciamento.controller.form.NovaSenhaUsuarioExternoForm;
import br.com.precredenciamento.controller.form.UsuarioExternoNomeSocialForm;
import br.com.precredenciamento.helper.ArquivoHelper;
import br.com.precredenciamento.helper.DataHelper;
import br.com.precredenciamento.model.Arquivo;
import br.com.precredenciamento.model.Codigo;
import br.com.precredenciamento.model.Endereco;
import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.model.UsuarioExternoNomeSocial;
import br.com.precredenciamento.repository.EnderecoRepository;
import br.com.precredenciamento.repository.UsuarioExternoNomeSocialRepository;
import br.com.precredenciamento.repository.UsuarioExternoRepository;
import br.com.precredenciamento.service.EmailService;
import br.com.precredenciamento.validacao.ValidacaoException;
import javassist.NotFoundException;

@Service
public class UsuarioExternoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;
	
	@Autowired
	private UsuarioExternoNomeSocialRepository usuarioExternoNomeSocialRepository; 

	@Autowired
	private CodigoService codigoService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private Validator validator;

	public UsuarioExterno atualizar(AtualizarUsuarioExternoFormData formData) throws JsonParseException, JsonMappingException, IOException, ValidacaoException {
		AtualizarUsuarioExternoForm form = new ObjectMapper().readValue(formData.getUsuario(), AtualizarUsuarioExternoForm.class);

		UsuarioExterno usuario = this.usuarioExternoRepository.findById(form.id).get();
		Endereco endereco = new Endereco();
		UsuarioExternoNomeSocial usuarioExternoNomeSocial = new UsuarioExternoNomeSocial();
		
		usuario.setEndereco(endereco);
		EnderecoForm enderecoForm = form.endereco;
		enderecoForm.setarPropriedades(endereco, validator);

		usuario.setCadastroNomeSocial(usuarioExternoNomeSocial);
		UsuarioExternoNomeSocialForm usuarioExternoNomeSocialForm = form.cadastroNomeSocial;
		usuarioExternoNomeSocialForm.setarPropriedades(usuarioExternoNomeSocial);

		form.setarPropriedades(usuario, validator);

		salvarNovoArquivos(usuario, formData);
		salvarAnexos(usuario, formData);

		enderecoRepository.save(endereco);
		usuarioExternoNomeSocialRepository.save(usuarioExternoNomeSocial);
		usuarioExternoRepository.save(usuario);

		return usuario;
	}

	private void salvarNovoArquivos(UsuarioExterno usuario, AtualizarUsuarioExternoFormData formData) {
		Class<?> classeUsuario = usuario.getClass();
		Class<?> classeFormData = formData.getClass();

		try {
			for (Field field : classeUsuario.getDeclaredFields()) {
				if (field.isAnnotationPresent(br.com.precredenciamento.anotacao.Arquivo.class)) {

					String campo = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
					String nomeMetodoGetArquivo = "get" + campo;

					Method getArquivoFormData = classeFormData.getMethod(nomeMetodoGetArquivo);
					MultipartFile multipartFile = (MultipartFile) getArquivoFormData.invoke(formData);

					if (multipartFile != null) {
						String nomeMetodoSetArquivo = "set" + campo;

						Method metodoGetArquivoUsuario = classeUsuario.getMethod(nomeMetodoGetArquivo);
						Method metodoSetArquivoUsuario = classeUsuario.getMethod(nomeMetodoSetArquivo, Arquivo.class);

						Arquivo arquivo = (Arquivo) metodoGetArquivoUsuario.invoke(usuario);

						if (arquivo != null) {
							arquivoService.removerPorId(arquivo.getId());
						}

						Arquivo aqruivoSalvo = arquivoService.salvar(multipartFile);
						metodoSetArquivoUsuario.invoke(usuario, aqruivoSalvo);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar arquivos");
		}
	}
	
	private void salvarAnexos(UsuarioExterno usuario, AtualizarUsuarioExternoFormData formData) {
		if (formData.getAnexos() == null ) {
			return;
		}
		
		if (formData.getAnexos().length <= 20) {
			usuario.getAnexos().forEach(anexo -> {
				arquivoService.removerPorId(anexo.getId());
			});
			
			usuario.setAnexos(new ArrayList<Arquivo>());
			
			for(MultipartFile file: formData.getAnexos()) {
				Arquivo arquivo = arquivoService.salvar(file);
				usuario.adicionarAnexo(arquivo);
			}
		} else {
			throw new IllegalArgumentException("Ultrapassou a quantidade máxima de 20 anexos");
		}
	}

	public UsuarioExterno salvar(CadastrarUsuarioExternoForm form) {		
		UsuarioExterno usuario = new UsuarioExterno();
		form.setarPropriedades(usuario);
		usuarioExternoRepository.save(usuario);
		if(form.email != "" && form.email != null) {
			enviarEmailPosCadastro(usuario);			
		}
		return usuario;
	}
	
	public void enviarEmailPosCadastro(UsuarioExterno usuarioExterno) {
		try {
			String dataComparecimento = DataHelper.formtarData(LocalDate.now().plusDays(30));
			
			String mensagem = ArquivoHelper.lerArquivo("texto-email-pos-cadastro.html");
			mensagem = mensagem.replace("[data-comparecimento]", dataComparecimento);
			
			emailService.send(mensagem, "SESC - Cadastro Realizado com Sucesso", usuarioExterno.getEmail());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Page<UsuarioExternoListagemDto> buscarTodos(
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
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
