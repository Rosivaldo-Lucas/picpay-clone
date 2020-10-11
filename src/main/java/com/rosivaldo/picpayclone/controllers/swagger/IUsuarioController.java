package com.rosivaldo.picpayclone.controllers.swagger;

import java.util.List;

import com.rosivaldo.picpayclone.dtos.ErroDTO;
import com.rosivaldo.picpayclone.dtos.UsuarioDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "/usuarios", description = "Operações relacionadas a usuários.")
public interface IUsuarioController {
  
  @ApiOperation(
    value = "Consultar saldo de um usuário por login",
    nickname = "consultarSaldo",
    notes = "",
    response = UsuarioDTO.class,
    responseContainer = "object",
    authorizations = { @Authorization(value = "Authorization") },
    tags = { "usuarios" }
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Saldo consultado com sucesso.", response = UsuarioDTO.class, responseContainer = "object"),
    @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes.", response = ErroDTO.class, responseContainer = "object"),
    @ApiResponse(code = 401, message = "Usuário sem permisão para acessar o recurso."),
    @ApiResponse(code = 404, message = "Usuário não encontrado."),
  })
  @GetMapping("/{login}/saldo")
  public ResponseEntity<UsuarioDTO> consultarSaldo(@PathVariable String login);

  @ApiOperation(
    value = "Consultar contatos de um usuário por login",
    nickname = "listarContatos",
    notes = "",
    response = UsuarioDTO.class,
    responseContainer = "object",
    authorizations = { @Authorization(value = "basicAuth") },
    tags = { "usuarios" }
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Contatos encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
    @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErroDTO.class, responseContainer = "object"),
    @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
    @ApiResponse(code = 404, message = "Usuário não encontrados")
  })
  @GetMapping("/contatos")
  public ResponseEntity<List<UsuarioDTO>> listar(@RequestParam String login);

  @ApiOperation(
    value = "Consultar usuário por login",
    nickname = "consultarUsuarios",
    notes = "",
    response = UsuarioDTO.class,
    responseContainer = "object",
    authorizations = { @Authorization(value = "basicAuth") },
    tags = { "usuarios" }
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Usuário encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
    @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErroDTO.class, responseContainer = "object"),
    @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
    @ApiResponse(code = 404, message = "Usuário não encontrada")
  })
  @GetMapping("/{login}")
  public ResponseEntity<UsuarioDTO> consultar(@PathVariable String login);

}
