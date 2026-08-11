package com.concessionaria.controller;

import com.concessionaria.dto.CarroRequestDto;
import com.concessionaria.dto.CarroResumoDto;
import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import com.concessionaria.service.CarroService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroResumoDto> adicionarCarro(@RequestBody @Valid CarroRequestDto dto){
        CarroResumoDto novoCarro = carroService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);

    }

    @GetMapping("/{id}")
    public CarroResumoDto pegarCarro(@PathVariable("id") Long id){

        return carroService.buscarPorId(id);
    }

    @GetMapping()
    public List<CarroResumoDto> todosCarros(){
        return carroService.listarTodos();
    }

    @PutMapping("/{id}")
    public Carro atualizarCarro(@PathVariable("id") Long id, @RequestBody @Valid CarroRequestDto dto){
        carro.setId(id);
        return carroRepository.save(carro);
    }


    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable("id") Long id){
        carroRepository.deleteById(id);
    }


}
