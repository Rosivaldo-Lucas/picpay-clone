package com.rosivaldo.picpayclone.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UsuarioDTO {
  
  @NotBlank
  private String login;

  private String senha;

  private String email;

  private String nomeCompleto;

  private String cpf;

  private OffsetDateTime ataNascimento;

  private String numeroTelefone;

  private BigDecimal saldo;

}
