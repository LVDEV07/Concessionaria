package com.concessionaria.service;

import com.concessionaria.dto.CarroRequestDto;
import com.concessionaria.dto.CarroResumoDto;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroResumoDto cadastrar(CarroRequestDto dto) {
        if (carroRepository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com esse chassi");
        }
        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com essa placa");
        }

        Carro carro = new Carro();
        carro.setNome(dto.nome());
        carro.setModelo(dto.modelo());
        carro.setMarca(dto.marca());
        carro.setAnoFabricacao(dto.anoFabricacao());
        carro.setAnoModelo(dto.anoModelo());
        carro.setCor(dto.cor());
        carro.setPlaca(dto.placa());
        carro.setChassi(dto.chassi());
        carro.setQuilometragem(dto.quilometragem());
        carro.setPreco(dto.preco());
        carro.setStatus(dto.status());
        carro.setCondicao(dto.condicao());

        return toDto(carroRepository.save(carro));
    }

    public CarroResumoDto buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));
        return toDto(carro);
    }

    public List<CarroResumoDto> listarTodos() {
        return carroRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public CarroResumoDto atualizar(Long id, CarroRequestDto dto) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));

        carroRepository.findByChassi(dto.chassi())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new RegistroDuplicadoException("Já existe um carro cadastrado com esse chassi");
                });

        if (dto.placa() != null) {
            carroRepository.findByPlaca(dto.placa())
                    .filter(outro -> !outro.getId().equals(id))
                    .ifPresent(outro -> {
                        throw new RegistroDuplicadoException("Já existe um carro cadastrado com essa placa");
                    });
        }

        carro.setNome(dto.nome());
        carro.setModelo(dto.modelo());
        carro.setMarca(dto.marca());
        carro.setAnoFabricacao(dto.anoFabricacao());
        carro.setAnoModelo(dto.anoModelo());
        carro.setCor(dto.cor());
        carro.setPlaca(dto.placa());
        carro.setChassi(dto.chassi());
        carro.setQuilometragem(dto.quilometragem());
        carro.setPreco(dto.preco());
        carro.setStatus(dto.status());
        carro.setCondicao(dto.condicao());

        return toDto(carroRepository.save(carro));
    }

    public void deletar(Long id) {
        carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado"));
        carroRepository.deleteById(id);
    }

    private CarroResumoDto toDto(Carro carro) {
        return new CarroResumoDto(
                carro.getId(),
                carro.getNome(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getStatus(),
                carro.getCondicao()
        );
    }
}