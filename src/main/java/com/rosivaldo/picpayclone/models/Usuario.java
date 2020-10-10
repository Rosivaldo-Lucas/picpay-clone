package com.rosivaldo.picpayclone.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usuario")
public class Usuario extends ModeloBase {
  
  @Column(name = "login", nullable = false)
  private String login;

  @Column(name = "senha", nullable = false)
  private String senha;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "nome_completo", nullable = false)
  private String nomeCompleto;

  @Column(name = "cpf", nullable = false)
  private String cpf;

  @Column(name = "data_nascimento", nullable = false)
  private OffsetDateTime dataNascimento;

  @Column(name = "numero_telefone", nullable = false)
  private String numeroTelefone;

  @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
  private List<CartaoCredito> cartoesCredito;

  @Column(name = "saldo", nullable = false)
  private BigDecimal saldo;

  @Column(name = "ativo", nullable = false)
  private Boolean ativo;

}
