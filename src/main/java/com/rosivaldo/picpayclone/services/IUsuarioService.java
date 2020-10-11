package com.rosivaldo.picpayclone.services;

import java.util.List;

import com.rosivaldo.picpayclone.dtos.UsuarioDTO;
import com.rosivaldo.picpayclone.models.Transacao;
import com.rosivaldo.picpayclone.models.Usuario;

public interface IUsuarioService {
  
  Usuario consultarEntidade(String login);

  void validar(Usuario...usuarios);

  void atualizarSaldo(Transacao transacao, Boolean isCartaoCredito);

  UsuarioDTO consultar(String login);

  List<UsuarioDTO> listar(String login);

}
