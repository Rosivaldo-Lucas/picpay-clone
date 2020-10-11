package com.rosivaldo.picpayclone.converters;

import com.rosivaldo.picpayclone.dtos.CartaoCreditoDTO;
import com.rosivaldo.picpayclone.dtos.TransacaoDTO;
import com.rosivaldo.picpayclone.models.CartaoCredito;
import com.rosivaldo.picpayclone.models.Transacao;
import com.rosivaldo.picpayclone.services.IUsuarioService;
import com.rosivaldo.picpayclone.utils.CartaoCreditoUtil;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoCreditoConversor extends ConversorBase<CartaoCredito, CartaoCreditoDTO> {

  @Autowired
	private IUsuarioService usuarioService;

	@Override
	public CartaoCreditoDTO converterEntidadeParaDTO(CartaoCredito entidade) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Transacao, TransacaoDTO>() {
			@Override
			protected void configure() { }
		});
		return modelMapper.map(entidade, CartaoCreditoDTO.class);
	}

	@Override
	public CartaoCredito converterDTOParaEntidade(CartaoCreditoDTO dto) {
		return CartaoCredito
				.builder()
				.bandeira(dto.getBandeira())
				.numero(CartaoCreditoUtil.mascarar(dto.getNumero()))
				.numeroToken(dto.getNumeroToken())
				.usuario(usuarioService.consultarEntidade(dto.getUsuario().getLogin()))
				.build();
	}
  
}
