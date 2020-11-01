package com.rosivaldo.picpayclone.services.impl;

import com.rosivaldo.picpayclone.config.MensagemValidacao;
import com.rosivaldo.picpayclone.models.Usuario;
import com.rosivaldo.picpayclone.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private IUsuarioService usuarioService;

  // USUARIO QUE QUER LOGAR NO SISTEMA
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Usuario usuario = usuarioService.consultarEntidade(login);

    if (!validarUsuario(usuario)) {
      throw new UsernameNotFoundException(MensagemValidacao.ERRO_USUARIO_SEM_PERMISSAO);
    }

    return usuario;
  }
 
  private Boolean validarUsuario(Usuario usuario) {
    Boolean usuarioValidado = false;

    if (usuario != null && usuario.getPermissao() != null && usuario.getAtivo()) {
      usuarioValidado = true;
    }

    return usuarioValidado;
  }

}
