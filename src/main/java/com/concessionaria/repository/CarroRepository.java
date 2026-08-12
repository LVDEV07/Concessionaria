package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);
    Optional<Carro> findByChassi(String chassi);
    Optional<Carro> findByPlaca(String placa);

    List<Carro> findByCor(String cor);
    List<Carro> findByAnoFabricacao(Integer anoFabricacao);
    List<Carro> findByCorAndAnoFabricacao(String cor, Integer anoFabricacao);
}