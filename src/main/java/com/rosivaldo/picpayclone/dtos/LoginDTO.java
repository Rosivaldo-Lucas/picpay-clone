package com.rosivaldo.picpayclone.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
  
  @NotBlank
  private String usuario;

  @NotBlank
  private String senha;

  public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(usuario, senha);
  }

}
