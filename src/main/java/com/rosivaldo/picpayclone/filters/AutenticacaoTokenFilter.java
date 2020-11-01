package com.rosivaldo.picpayclone.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rosivaldo.picpayclone.models.Usuario;
import com.rosivaldo.picpayclone.repositories.UsuarioRepository;
import com.rosivaldo.picpayclone.services.impl.TokenService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private UsuarioRepository usuarioRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  // TODOS OS ENDPOINTS DA APLICACAO VAO PASSAR POR ESSE METODO
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {


    String token = recuperarToken(request); 
    Boolean tokenValido = tokenService.isTokenValido(token);

    if (tokenValido) {
      autenticarUsuario(token);
    }

    filterChain.doFilter(request, response);
  }

  private void autenticarUsuario(String token) {
    Long idUsuario = tokenService.getIdUsuario(token);
    Usuario usuario = usuarioRepository.findById(idUsuario).get();

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    
    if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring(7, token.length());
  }
  
}
