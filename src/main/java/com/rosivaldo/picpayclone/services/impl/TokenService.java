package com.rosivaldo.picpayclone.services.impl;

import java.util.Date;

import com.rosivaldo.picpayclone.models.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

  @Value("${picpayclone.jwt.expiration}")
  private String expiration;

  @Value("${picpayclone.jwt.secret-key}")
  private String secretKey;

	public String gerarToken(Authentication authenticate) {
    Usuario usuarioLogado = (Usuario) authenticate.getPrincipal();
    
    Date dataHoje = new Date();
    Date tempoExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));

    String token = Jwts.builder()
      .setIssuer("API PicPay Clone")
      .setSubject(usuarioLogado.getId().toString())
      .setIssuedAt(tempoExpiracao)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();

    return token;
	}

	public Boolean isTokenValido(String token) {
    
    try {
      Jwts.parser()
      .setSigningKey(this.secretKey)
      .parseClaimsJws(token);

      return true;
    } catch (Exception exception) {
      return false;
    }
    
	}

	public Long getIdUsuario(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(this.secretKey)
      .parseClaimsJws(token)
      .getBody();

    return Long.parseLong(claims.getSubject());
  }
  
}
