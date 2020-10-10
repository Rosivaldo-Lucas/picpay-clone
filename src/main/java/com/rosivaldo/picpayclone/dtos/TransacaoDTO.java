package com.rosivaldo.picpayclone.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TransacaoDTO {
  
  @NotBlank
  private String codigo;

  @NotNull
  private UsuarioDTO origem;

  @NotNull
  private UsuarioDTO destino;

  @NotNull
  private OffsetDateTime dataHora;

  @NotNull
  private BigDecimal valor;

  private CartaoCreditoDTO cartaoCredito;

  private Boolean isCartaoCredito = false;

}
