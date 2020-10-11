package com.rosivaldo.picpayclone.services.impl;

import com.rosivaldo.picpayclone.converters.TransacaoConversor;
import com.rosivaldo.picpayclone.dtos.TransacaoDTO;
import com.rosivaldo.picpayclone.models.Transacao;
import com.rosivaldo.picpayclone.repositories.TransacaoRepository;
import com.rosivaldo.picpayclone.services.ICartaoCreditoService;
import com.rosivaldo.picpayclone.services.ITransacaoService;
import com.rosivaldo.picpayclone.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService implements ITransacaoService {

  @Autowired
  private TransacaoConversor transacaoConversor;

  @Autowired
  private IUsuarioService usuarioService;

  @Autowired
  private ICartaoCreditoService cartaoCreditoService;

  @Autowired
  private TransacaoRepository transacaoRepository;

  /**
   * Passos para processar uma transação: 1 -> Salvar transação e cartão de
   * crédito. 2 -> Atualizar o saldo do usuário. 3 -> Retornar a transação.
   */
  @Override
  public TransacaoDTO processar(TransacaoDTO transacaoDTO) {
    Transacao transacao = salvar(transacaoDTO);

    cartaoCreditoService.salvar(transacaoDTO.getCartaoCredito());

    usuarioService.atualizarSaldo(transacao, transacaoDTO.getIsCartaoCredito());

    return transacaoConversor.converterEntidadeParaDTO(transacao);
  }

  private Transacao salvar(TransacaoDTO transacaoDTO) {
    Transacao transacao = transacaoConversor.converterDTOParaEntidade(transacaoDTO);

    usuarioService.validar(transacao.getDestino(), transacao.getOrigem());

    return transacaoRepository.save(transacao);
  }

  @Override
  public Page<TransacaoDTO> listar(Pageable paginacao, String login) {
    Page<Transacao> transacoes = transacaoRepository.findByOrigem_LoginOrDestino_Login(login, login, paginacao);
    
    return transacaoConversor.converterPageEntidadeParaDTO(transacoes);
  }
  
}
