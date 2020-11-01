package com.rosivaldo.picpayclone.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rosivaldo.picpayclone.enums.TipoPermissao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario extends ModeloBase implements UserDetails {

  private static final long serialVersionUID = 1L;

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

  @Enumerated(EnumType.STRING)
  @Column(name = "permissao", nullable = false)
  private TipoPermissao permissao;

  // QUAIS AS PERMISSOES DO USUARIO
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(permissao.getDescricao()));
  }

  // QUAL A SENHA
  @Override
  public String getPassword() {
    return this.getSenha();
  }

  // QUAL O LOGIN / USERNAME / EMAIL
  @Override
  public String getUsername() {
    return this.getLogin();
  }

  // METODO PARA INFORMAR QUANDO O USUARIO EXPIROU
  @Override
  public boolean isAccountNonExpired() {
    return true; // O USUARIO SEMPRE VAI TA LOGADO
  }

  // METODO PARA SABER COM USUARIO ESTA BLOQUEADO
  @Override
  public boolean isAccountNonLocked() {
    return true; // USUARIO NUNCA ESTA BLOQUEADO
  }

  // METODO PARA DIZER AS CREDENCIAIS EXPIRADAS
  @Override
  public boolean isCredentialsNonExpired() {
    return true; // USAURIO NAO TEM CREDIENCIAS EXPIRADAS
  }

  // METODO PARA DIZER SE O USUARIO ESTA ATIVO OU NAO
  @Override
  public boolean isEnabled() {
    return this.ativo;
  }

}
