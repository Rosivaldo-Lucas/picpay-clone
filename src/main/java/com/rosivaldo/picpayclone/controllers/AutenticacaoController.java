package com.rosivaldo.picpayclone.controllers;

import com.rosivaldo.picpayclone.dtos.LoginDTO;
import com.rosivaldo.picpayclone.dtos.TokenDTO;
import com.rosivaldo.picpayclone.services.impl.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController extends ControllerBase<TokenDTO> {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  public ResponseEntity<TokenDTO> autenticar(@RequestBody LoginDTO loginDTO) {

    UsernamePasswordAuthenticationToken login = loginDTO.converter();

    try {

      Authentication authenticate = authenticationManager.authenticate(login);
      String token = tokenService.gerarToken(authenticate);

      return responderSucessoComItem(new TokenDTO(token, "Bearer"));

    } catch (AuthenticationException exception) {

      return responderRequisicaoMalSucedida();

    }
    
  }

}
