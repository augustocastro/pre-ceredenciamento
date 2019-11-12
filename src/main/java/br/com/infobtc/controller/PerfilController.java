package br.com.infobtc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.PerfilDto;
import br.com.infobtc.controller.dto.PerfilUsuariosDto;
import br.com.infobtc.controller.dto.UsuarioPerfilDto;
import br.com.infobtc.controller.form.PerfilForm;
import br.com.infobtc.controller.vo.UsuarioPerfilPorcentagemVo;
import br.com.infobtc.controller.vo.UsuarioPerfilVo;
import br.com.infobtc.dao.PerfilDao;
import br.com.infobtc.model.Funcionalidade;
import br.com.infobtc.model.Perfil;
import br.com.infobtc.repository.FuncionalidadeRepository;
import br.com.infobtc.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository; 
	
	@Autowired
	private PerfilDao perfilDao;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PerfilDto> cadastrar(@RequestBody @Valid PerfilForm perfilForm, UriComponentsBuilder uriComponentsBuilder) {
		Perfil perfil = new Perfil();
		
		salvar(perfilForm, perfil);
		perfilRepository.save(perfil);
		
		URI uri = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
		return ResponseEntity.created(uri).body(new PerfilDto().converter(perfil));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PerfilDto> atualizar(@PathVariable Long id, @Valid @RequestBody PerfilForm perfilForm) {
		Optional<Perfil> optional = perfilRepository.findById(id);
		
		if (optional.isPresent()) {
			Perfil perfil = optional.get(); 
			
			funcionalidadeRepository.deleteAll(perfil.getFuncionalidades());
			salvar(perfilForm, perfil);
		
			return ResponseEntity.ok(new PerfilDto().converter(perfil));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<PerfilDto>> buscarTodos() {
		List<Perfil> perfis = perfilRepository.findAll();
		return ResponseEntity.ok(new PerfilDto().converterPerfisLista(new ArrayList<Perfil>(perfis)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PerfilDto> buscarPorId(@PathVariable Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);
		
		if (perfil.isPresent()) {
			return ResponseEntity.ok(new PerfilDto().converter(perfil.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (perfilRepository.findById(id).isPresent()) {
			perfilRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/relatorio/perfil-usuarios")
	public ResponseEntity<?> buscarUsuariosAgrupandoPorPerfil() {		
		List<UsuarioPerfilVo> resultado = perfilDao.buscarUsuariosAgrupandoPorPerfil();
		
		Map<String, List<UsuarioPerfilDto>> perfilUsuariosMap = resultado
				.stream()
				.collect(Collectors.groupingBy(UsuarioPerfilVo::getNomePerfil, Collectors
						.mapping(UsuarioPerfilDto::new, Collectors.toList())));
		
		List<PerfilUsuariosDto> perfilUsuarios = new ArrayList<PerfilUsuariosDto>();
		
		perfilUsuariosMap.forEach(( key, value ) -> {
			perfilUsuarios.add(new PerfilUsuariosDto(key, value));
		});

		return ResponseEntity.ok(perfilUsuarios);
	}
	
	@GetMapping("/relatorio/perfil-percentual-usuarios")
	public ResponseEntity<?> buscarPerfisCalculandoPorcentualUsuarios() {		
		List<UsuarioPerfilPorcentagemVo> resultado = perfilDao.buscarRelacaoPerfis();
		return ResponseEntity.ok(resultado);
	}
	
	private void salvar(PerfilForm perfilForm, Perfil perfil) {
		Set<Funcionalidade> funcionalidades = new HashSet<Funcionalidade>();

		perfilForm.getFuncionalidades().forEach(funcionalidadePermissaoForm -> {
			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidadePermissaoForm.setarPropriedades(funcionalidade);
			funcionalidades.add(funcionalidade);
		});
		
		perfilForm.setarPropriedades(perfil);
		perfil.setFuncionalidades(funcionalidades);
		funcionalidadeRepository.saveAll(funcionalidades);
	}
	
}
