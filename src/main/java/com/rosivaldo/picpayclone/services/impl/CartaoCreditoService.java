package com.rosivaldo.picpayclone.services.impl;

import com.rosivaldo.picpayclone.converters.CartaoCreditoConversor;
import com.rosivaldo.picpayclone.dtos.CartaoCreditoDTO;
import com.rosivaldo.picpayclone.models.CartaoCredito;
import com.rosivaldo.picpayclone.repositories.CartaoCreditoRepository;
import com.rosivaldo.picpayclone.services.ICartaoCreditoService;
import com.rosivaldo.picpayclone.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoService implements ICartaoCreditoService {

  @Autowired
  private CartaoCreditoRepository cartaoCreditoRepository;

  @Autowired
  private CartaoCreditoConversor cartaoCreditoConversor;

  @Autowired
  private IUsuarioService usuarioService;

  @Override
  public CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCreditoDTO) {
    CartaoCreditoDTO cartaoCreditoRetornoDTO = null;

    if (cartaoCreditoDTO.getIsSalva()) {
      CartaoCredito cartaoCredito = cartaoCreditoConversor.converterDTOParaEntidade(cartaoCreditoDTO);
      usuarioService.validar(cartaoCredito.getUsuario());
      
      CartaoCredito cartaoCreditoSalvo = cartaoCreditoRepository.save(cartaoCredito);
      cartaoCreditoRetornoDTO = cartaoCreditoConversor.converterEntidadeParaDTO(cartaoCreditoSalvo);
    }

    return cartaoCreditoRetornoDTO;
  }
  
}
