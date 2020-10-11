package com.rosivaldo.picpayclone.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <E> Entidade
 * @param <D> DTO
 */

public abstract class ConversorBase<E, D> {
  
  public abstract E converterDTOParaEntidade(D dto);

  public abstract D converterEntidadeParaDTO(E entidade);

  public List<E> converterDTOsParaEntidades(List<D> dtos) {
    List<E> entidades = new ArrayList<>();

    dtos.stream().forEach((dto) -> entidades.add(converterDTOParaEntidade(dto)));

    return entidades;
  }

  public List<D> converterEntidadesParaDTOs(List<E> entidades) {
    List<D> dtos = new ArrayList<>();

    entidades.stream().forEach((entidade) -> dtos.add(converterEntidadeParaDTO(entidade)));

    return dtos;
  }

}
