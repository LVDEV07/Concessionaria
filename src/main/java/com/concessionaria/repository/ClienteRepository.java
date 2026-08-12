package com.concessionaria.repository;

import com.concessionaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);
}