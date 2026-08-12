package com.concessionaria.service;

import com.concessionaria.dto.CarroRequestDto;
import com.concessionaria.dto.CarroResumoDto;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public CarroResumoDto cadastrar (CarroRequestDto dto){
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

        Carro salvo = carroRepository.save(carro);

        return new CarroResumoDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getModelo(),
                salvo.getMarca(),
                salvo.getAnoFabricacao(),
                salvo.getAnoModelo(),
                salvo.getCor(),
                salvo.getPlaca(),
                salvo.getChassi(),
                salvo.getQuilometragem(),
                salvo.getPreco(),
                salvo.getStatus(),
                salvo.getCondicao()

        );

    }

    public CarroResumoDto buscarPorId (Long id){
    Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro não encontrado"));

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

    public List<CarroResumoDto> listarTodos(){
        List<Carro> carros = carroRepository.findAll();

        return carros.stream().map(carro ->new CarroResumoDto(carro.getId(),
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
                carro.getCondicao())).toList();
    }

    public CarroResumoDto atualizar(Long id, CarroRequestDto dto){

        Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carro.setId(id);
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

        carroRepository.save(carro);

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

    public void deletar(Long id){
        carroRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));
        carroRepository.deleteById(id);

    }


}
