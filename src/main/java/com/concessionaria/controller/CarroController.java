package com.concessionaria.controller;

import com.concessionaria.dto.CarroRequestDto;
import com.concessionaria.dto.CarroResumoDto;
import com.concessionaria.service.CarroService;
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

@Tag(name = "Carros", description = "Consulta e manutenção do estoque de carros")
@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Operation(summary = "Cadastra um carro", description = "Adiciona um novo carro ao estoque.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Carro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição"),
            @ApiResponse(responseCode = "409", description = "Chassi ou placa já cadastrados")
    })
    @PostMapping
    public ResponseEntity<CarroResumoDto> adicionarCarro(@RequestBody @Valid CarroRequestDto dto){
        CarroResumoDto novoCarro = carroService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @Operation(summary = "Busca um carro por id", description = "Retorna os dados de um carro específico do estoque.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    @GetMapping("/{id}")
    public CarroResumoDto pegarCarro(@PathVariable("id") Long id){
        return carroService.buscarPorId(id);
    }

    @Operation(summary = "Lista os carros do estoque", description = "Retorna todos os carros, podendo ser filtrado por cor ou ano de fabricação. Sem filtro, lista todos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping
    public List<CarroResumoDto> todosCarros(
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano){
        return carroService.buscarComFiltro(cor, ano);
    }

    @Operation(summary = "Atualiza um carro", description = "Atualiza os dados de um carro existente no estoque.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Chassi ou placa já cadastrados em outro carro")
    })
    @PutMapping("/{id}")
    public CarroResumoDto atualizarCarro(@PathVariable("id") Long id, @RequestBody @Valid CarroRequestDto dto){
        return carroService.atualizar(id, dto);
    }

    @Operation(summary = "Remove um carro", description = "Remove um carro do estoque pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable("id") Long id){
        carroService.deletar(id);
    }
}