package com.rosivaldo.picpayclone.exceptions;

public class NegocioExceptions extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NegocioExceptions(String mensagem) {
    super(mensagem);
  }

  public NegocioExceptions(String mensagem, Exception exception) {
    super(mensagem, exception);
  }

}
