package com.rosivaldo.picpayclone.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rosivaldo.picpayclone.converters.UsuarioConversor;
import com.rosivaldo.picpayclone.dtos.UsuarioDTO;
import com.rosivaldo.picpayclone.exceptions.NegocioExceptions;
import com.rosivaldo.picpayclone.models.Transacao;
import com.rosivaldo.picpayclone.models.Usuario;
import com.rosivaldo.picpayclone.repositories.UsuarioRepository;
import com.rosivaldo.picpayclone.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private UsuarioConversor usuarioConversor;

  @Override
  public Usuario consultarEntidade(final String login) {
    return usuarioRepository.findByLogin(login);
  }

  @Override
  public void validar(Usuario... usuarios) {
    Arrays.asList(usuarios).stream().forEach((usuario) -> {
      if (usuario == null) {
        throw new NegocioExceptions("O usuário não exixste.");
      }
    });
  }

  @Override
  @Async("asyncExecutor")
  public void atualizarSaldo(Transacao transacao, Boolean isCartaoCredito) {
    decrementarSaldo(transacao, isCartaoCredito);
    incrementarSaldo(transacao);
  }

  private void incrementarSaldo(Transacao transacao) {
    usuarioRepository.updateIncrementarSaldo(transacao.getDestino().getLogin(), transacao.getValor());
  }

  private void decrementarSaldo(Transacao transacao, Boolean isCartaoCredito) {
    if (!isCartaoCredito) {
      usuarioRepository.updateDecrementarSaldo(transacao.getOrigem().getLogin(), transacao.getValor());
    }
  }

  @Override
  public UsuarioDTO consultar(String login) {
    Usuario usuario = consultarEntidade(login);

    return usuarioConversor.converterEntidadeParaDTO(usuario);
  }

  @Override
  public List<UsuarioDTO> listar(String login) {
    List<Usuario> usuarios = usuarioRepository.findAll();
  
    List<Usuario> usuariosFiltrados = usuarios.stream()
      .filter((usuario) -> !usuario.getLogin().equals(login))
      .collect(Collectors.toList());

    return usuarioConversor.converterEntidadesParaDTOs(usuariosFiltrados);
  }

}
