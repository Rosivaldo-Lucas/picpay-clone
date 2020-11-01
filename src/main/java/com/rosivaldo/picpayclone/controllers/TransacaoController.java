package com.rosivaldo.picpayclone.controllers;

import javax.validation.Valid;

import com.rosivaldo.picpayclone.dtos.TransacaoDTO;
import com.rosivaldo.picpayclone.services.ITransacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController extends ControllerBase<TransacaoDTO> {
  
  @Autowired
  private ITransacaoService transacaoService;

  @GetMapping
  @Cacheable(cacheNames = "Transacoes", key = "#root.method.name")
  public ResponseEntity<Page<TransacaoDTO>> listar(@PageableDefault(page = 0, size = 20) Pageable paginacao, @RequestParam String login) {
    Page<TransacaoDTO> transacoes = transacaoService.listar(paginacao, login);
  
    return responderListaDeItensPaginada(transacoes);
  }

  @PostMapping
  @CacheEvict(cacheNames = "Transacoes", allEntries = true) // LIMPA O CHCE SEMPRE QUE FAZER UMA TRANSACAO
  public ResponseEntity<TransacaoDTO> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO, UriComponentsBuilder uriBuilder){
    TransacaoDTO transacaoRetornoDTO = transacaoService.processar(transacaoDTO);
  
    return responderItemCriadoComURI(transacaoRetornoDTO, uriBuilder, "/transacoes/{codigo}", transacaoRetornoDTO.getCodigo());
  }

}
