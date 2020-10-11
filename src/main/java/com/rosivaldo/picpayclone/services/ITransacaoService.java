package com.rosivaldo.picpayclone.services;

import com.rosivaldo.picpayclone.dtos.TransacaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransacaoService {
  
  TransacaoDTO processar(TransacaoDTO transacaoDTO);

  Page<TransacaoDTO> listar(Pageable paginacao, String login);

}
