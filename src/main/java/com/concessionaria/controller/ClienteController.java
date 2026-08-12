package com.concessionaria.controller;

import com.concessionaria.dto.ClienteRequestDto;
import com.concessionaria.dto.ClienteResumoDto;
import com.concessionaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Cadastro e consulta de clientes da concessionária")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cadastra um cliente", description = "Adiciona um novo cliente à base.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    })
    @PostMapping
    public ResponseEntity<ClienteResumoDto> adicionarCliente(@RequestBody @Valid ClienteRequestDto dto){
        ClienteResumoDto novoCliente = clienteService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Operation(summary = "Busca um cliente por id", description = "Retorna os dados de um cliente específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ClienteResumoDto pegarCliente(@PathVariable("id") Long id){
        return clienteService.buscarPorId(id);
    }

    @Operation(summary = "Lista os clientes", description = "Retorna todos os clientes cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping
    public List<ClienteResumoDto> todosClientes(){
        return clienteService.listarTodos();
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado em outro cliente")
    })
    @PutMapping("/{id}")
    public ClienteResumoDto atualizarCliente(@PathVariable("id") Long id, @RequestBody @Valid ClienteRequestDto dto){
        return clienteService.atualizar(id, dto);
    }

    @Operation(summary = "Remove um cliente", description = "Remove um cliente pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable("id") Long id){
        clienteService.deletar(id);
    }
}