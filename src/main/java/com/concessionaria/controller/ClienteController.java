package com.concessionaria.controller;

import com.concessionaria.dto.ClienteRequestDto;
import com.concessionaria.dto.ClienteResumoDto;
import com.concessionaria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResumoDto> adicionarCliente(@RequestBody @Valid ClienteRequestDto dto){
        ClienteResumoDto novoCliente = clienteService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/{id}")
    public ClienteResumoDto pegarCliente(@PathVariable("id") Long id){
        return clienteService.buscarPorId(id);
    }

    @GetMapping()
    public List<ClienteResumoDto> todosClientes(){
        return clienteService.listarTodos();
    }

    @PutMapping("/{id}")
    public ClienteResumoDto atualizarCliente(@PathVariable("id") Long id, @RequestBody @Valid ClienteRequestDto dto){
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable("id") Long id){
        clienteService.deletar(id);
    }
}