package com.rosivaldo.picpayclone.enums;

public enum TipoPermissao {
  
  USUARIO("ROLE_USER"),
  ADMINISTRADOR("ROLE_ADMIN");

  private String descricao;

  TipoPermissao(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
