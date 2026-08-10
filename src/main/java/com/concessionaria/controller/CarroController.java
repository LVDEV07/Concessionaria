package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public Carro adicionarCarro(@RequestBody Carro carro){
        return carroRepository.save(carro);
    }

    @GetMapping("/{id}")
    public Carro pegarCarro(@PathVariable("id") Long id){
        return carroRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<Carro> todosCarros(){
        return carroRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable("id") Long id){
        carroRepository.deleteById(id);
    }


}
