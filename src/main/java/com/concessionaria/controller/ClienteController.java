package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente pegarCliente(@PathVariable("id") Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<Cliente> todosClientes(){
        return clienteRepository.findAll();
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable("id") Long id){
        clienteRepository.deleteById(id);
    }
}
