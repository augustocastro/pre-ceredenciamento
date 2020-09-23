package br.com.precredenciamento.job;

import org.quartz.JobExecutionContext;

public class Job implements org.quartz.Job {

//	@Autowired
//	private ContaRepository contaRepository;
//
//	@Autowired
//	private DadosHashRepository dadosHashRepository;
//
//	@Autowired
//	private ParcelaRepository parcelaRepository;
//	
//	@Autowired
//	private ContratoRepository contratoRepository;
//	
//	@Autowired
//	private ContratoDao contratoDao; 
//	
//	@Autowired
//	private ContratoService contratoService;
//	
	@Override
	public void execute(JobExecutionContext context) {
//
//		// CONTRATOS
//		System.out.println("##########################################");
//		System.out.println("APLICANDO RENDIMENTO AOS INVESTIMENTOS COMPOSTOS: " + LocalDateTime.now().toString());
//		
//		contratoService.aplicarRedimentoComposto(contratoDao.buscarContratosCompostoAniversario());
//		
//		System.out.println("##########################################");
//
//		System.out.println();
//		
//    	System.out.println("##########################################");
//    	System.out.println("ENCERRANDO CONTRATOS: " + LocalDateTime.now().toString());
//    	contratoService.finalizarContrato(contratoRepository.findByDtTermino(LocalDate.now()));
//    	System.out.println("##########################################");
//
//		
//		// CONTAS
//		System.out.println("##########################################");
//		System.out.println("ATRASANDO CONTAS: " + LocalDateTime.now().toString());
//		
//		contaRepository.buscaContasAtrasadas(LocalDate.now()).forEach(conta -> {
//			System.out.println("Conta: " + conta.getId());
//			conta.setStatus(StatusConta.EM_ATRASO);
//			contaRepository.save(conta);
//		});
//		
//		System.out.println("##########################################");
//		
//		System.out.println();
//		
//		// PARCELAS
//		System.out.println("##########################################");
//		System.out.println("ATRASANDO PARCELAS: " + LocalDateTime.now().toString());
//		
//		parcelaRepository.buscarParcelasAtrasadas(LocalDate.now()).forEach(parcela -> {
//			System.out.println("Parcela: " + parcela.getId());
//			parcela.setStatus(StatusRepasse.A_EXECUTAR);
//			parcelaRepository.save(parcela);
//		});
//
//		System.out.println("##########################################");
//		
//		System.out.println();
//		
//		// HASHS
//    	System.out.println("##########################################");
//    	System.out.println("APAGANDO HASHS: " + LocalDateTime.now().toString());
//    	dadosHashRepository.deleteAll();
//    	System.out.println("##########################################");
	}

}
