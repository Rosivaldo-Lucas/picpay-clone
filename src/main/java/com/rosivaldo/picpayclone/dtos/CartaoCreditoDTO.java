package com.rosivaldo.picpayclone.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.rosivaldo.picpayclone.enums.BandeiraCartao;

import lombok.Data;

@Data
public class CartaoCreditoDTO {
  
  @NotBlank
  private BandeiraCartao bandeira;

  @NotBlank
  private String dataExpiracao;

  @NotBlank
  private String nomeTitular;
  
  private String numero;

  private String numeroToken;

  @NotNull
  private UsuarioDTO usuario;

  private Boolean isSalva = false;

}
