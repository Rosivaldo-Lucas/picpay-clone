package com.rosivaldo.picpayclone.converters;

import com.rosivaldo.picpayclone.dtos.UsuarioDTO;
import com.rosivaldo.picpayclone.models.Usuario;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConversor extends ConversorBase<Usuario, UsuarioDTO> {
  
  @Override
	public UsuarioDTO converterEntidadeParaDTO(Usuario entidade) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Usuario, UsuarioDTO>() {
			@Override
			protected void configure() { }
		});
		return modelMapper.map(entidade, UsuarioDTO.class);
	}

	@Override
	public Usuario converterDTOParaEntidade(UsuarioDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<UsuarioDTO, Usuario>() {
			@Override
			protected void configure() { }
		});
		return modelMapper.map(dto, Usuario.class);
  }
  
}
