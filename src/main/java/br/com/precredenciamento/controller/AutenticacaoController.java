package br.com.precredenciamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private TokenService tokenService;

//	@PostMapping
//	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
//		UsernamePasswordAuthenticationToken authenticationToken = form.converter();
//
//		try {
//			Authentication authentication = authenticationManager.authenticate(authenticationToken);
//			String token = tokenService.gerarToken(authentication);
//			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
//		} catch (AuthenticationException e) {
//			return ResponseEntity.badRequest().build();
//		}
//	}
	
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    
//    @Autowired
//    JwtTokenProvider tokenProvider;

//	@PostMapping("/generatetoken")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginUsuarioExternoForm loginRequest) {
//		System.out.println(loginRequest);
//    	if(loginRequest.getUsername().isEmpty() || loginRequest.getPassword().isEmpty()) {
//    		return ResponseEntity.badRequest().build();
//    	}
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//        String jwt = tokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
//    }

//    @SuppressWarnings({ "unchecked", "rawtypes" })
//	@PostMapping("/validatetoken")
//    public ResponseEntity<?> getTokenByCredentials(@Valid @RequestBody ValidateTokenRequest validateToken) {
//    	 String username = null;
//    	 String jwt =validateToken.getToken();
//         if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//                username = tokenProvider.getUsernameFromJWT(jwt);
//	          //If required we can have one more check here to load the user from LDAP server
//             return ResponseEntity.ok(new ApiResponse(Boolean.TRUE,MessageConstants.VALID_TOKEN + username));
//           }else {
//        	   return new ResponseEntity(new ApiResponse(false, MessageConstants.INVALID_TOKEN),
//                       HttpStatus.BAD_REQUEST);
//           }
//         
//    }
	
}
