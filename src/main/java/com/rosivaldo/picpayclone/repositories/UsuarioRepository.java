package com.rosivaldo.picpayclone.repositories;

import java.math.BigDecimal;

import com.rosivaldo.picpayclone.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  
  Usuario findByLogin(String login);

  @Modifying
  @Query("UPDATE Usuario u SET u.saldo = u.saldo - ?2 WHERE u.login = ?1")
  void updateIncrementarSaldo(String login, BigDecimal valor);

  @Modifying
  @Query("UPDATE Usuario u SET u.saldo = u.saldo + ?2 WHERE u.login = ?1")
  void updateDecrementarSaldo(String login, BigDecimal valor);

}
