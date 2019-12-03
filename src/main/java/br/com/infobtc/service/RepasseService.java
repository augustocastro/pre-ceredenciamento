package br.com.infobtc.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.infobtc.config.security.service.TokenService;
import br.com.infobtc.controller.form.RepasseEmLoteForm;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Parcela;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.StatusRepasse;
import br.com.infobtc.model.TipoRecebedor;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.ParcelaRepository;
import br.com.infobtc.repository.RepasseRepository;
import br.com.infobtc.repository.UsuarioRepository;
import javassist.NotFoundException;

@Service
public class RepasseService {

	@Autowired
	private RepasseRepository repasseRepository;

	@Autowired
	private ParcelaRepository parcelaRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TokenService tokenService;

	public void salvarRepasseEmLote(HttpServletRequest request, RepasseEmLoteForm repasseForm)
			throws MalformedURLException, NotFoundException {

		for (Long parcela_id : repasseForm.getParcelas_id()) {
			Optional<Parcela> optional = parcelaRepository.findById(parcela_id);

			if (optional.isPresent()) {
				Repasse repasse = new Repasse();
				TipoRecebedor tipoRecebedor = repasseForm.getTipo_recebedor();
				Parcela parcela = optional.get();
				Contrato contrato = parcela.getContrato();

				repasse.setObservacao(repasseForm.getObservacao());
				repasse.setData(LocalDate.parse(repasseForm.getData()));
				repasse.setTipoRecebedor(tipoRecebedor);
				repasse.setTipoRepasse(repasseForm.getTipo_repasse());
				repasse.setStatus(repasseForm.getStatus());
				repasse.setParcela(parcela);

				double valorContrato = contrato.getValor().doubleValue();

				if (tipoRecebedor == TipoRecebedor.INVESTIDOR) {
					repasse.setRecebedor(contrato.getInvestidor().getNome());
					repasse.setValor(valorContrato * 0.1);
				} else if (tipoRecebedor == TipoRecebedor.ESCRITORIO && parcela.getParcela() == 1) {
					repasse.setRecebedor("Escritório");
					repasse.setValor(valorContrato * 0.01);
				} else if (tipoRecebedor == TipoRecebedor.CONSULTOR && parcela.getParcela() > 1) {
					repasse.setRecebedor(contrato.getConsultor().getNome());
					repasse.setValor(valorContrato * contrato.getConsultor().getPorcentagem());
				} else {
					continue;
				}
				
				parcela.setStatus(StatusRepasse.EXECUTADO);
				parcela.getRepasses().add(repasse);
				salvarRepasseComAnexos(request, repasse, repasseForm.getAnexo());
				parcelaRepository.save(parcela);

			} else {
				continue;
			}
		}

	}

	public void salvarRepasseComAnexos(HttpServletRequest request, Repasse repasse, MultipartFile anexo)
			throws NotFoundException, MalformedURLException {
		setarUsuario(request, repasse);

		if (anexo != null) {
			URI uploadFile = s3Service.uploadFile(anexo);
			repasse.setAnexo(uploadFile.toURL().toString());
		}

		repasseRepository.save(repasse);
	}

	public void setarUsuario(HttpServletRequest request, Repasse repasse) throws NotFoundException {
		String token = tokenService.recuperarToken(request);
		Long usuarioId = tokenService.getUsuario(token);

		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			repasse.setUsuario(usuario.get());
		} else {
			throw new NotFoundException(String.format("O usuário de id %s não foi encontrado.", usuarioId));
		}
	}
}
