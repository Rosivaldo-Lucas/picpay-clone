package com.rosivaldo.picpayclone.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "transacao")
public class Transacao extends ModeloBase {
  
  @Column(name = "codigo", nullable = false)
  private String codigo;

  @ManyToOne(cascade = { CascadeType.MERGE })
  @JoinColumn(name = "usuario_origem", nullable = false)
  private Usuario origem;

  @ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
  @JoinColumn(name = "usuario_destino", nullable = false)
  private Usuario destino;
  
  @Column(name = "data_hora", nullable = false)
  private OffsetDateTime dataHora;

  @Column(name = "valor", nullable = false)
  private BigDecimal valor;

}
