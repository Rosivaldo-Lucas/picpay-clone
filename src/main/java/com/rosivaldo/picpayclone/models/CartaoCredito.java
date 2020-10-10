package com.rosivaldo.picpayclone.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rosivaldo.picpayclone.enums.BandeiraCartao;

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
@Table(name = "cartao_credito")
public class CartaoCredito extends ModeloBase {
  
  @Column(name = "numero", nullable = false)
  private String numero;

  @Enumerated(EnumType.STRING)
  @Column(name = "bandeira", nullable = false)
  private BandeiraCartao bandeira;

  @Column(name = "token")
  private String numeroToken;

  @ManyToOne(cascade = { CascadeType.MERGE })
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

}
