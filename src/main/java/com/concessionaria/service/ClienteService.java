package com.concessionaria.service;

import com.concessionaria.dto.ClienteRequestDto;
import com.concessionaria.dto.ClienteResumoDto;
import com.concessionaria.exception.CpfInvalidoException;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResumoDto cadastrar(ClienteRequestDto dto) {
        validarCpfSoNumeros(dto.cpf());

        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("Já existe um cliente cadastrado com esse CPF");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        return toDto(clienteRepository.save(cliente));
    }

    public ClienteResumoDto buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return toDto(cliente);
    }

    public List<ClienteResumoDto> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ClienteResumoDto atualizar(Long id, ClienteRequestDto dto) {
        validarCpfSoNumeros(dto.cpf());

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        clienteRepository.findByCpf(dto.cpf())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new RegistroDuplicadoException("Já existe um cliente cadastrado com esse CPF");
                });

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        return toDto(clienteRepository.save(cliente));
    }

    public void deletar(Long id) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }

    private void validarCpfSoNumeros(String cpf) {
        for (char c : cpf.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new CpfInvalidoException("CPF deve conter somente números, sem letras ou símbolos");
            }
        }
    }

    private ClienteResumoDto toDto(Cliente cliente) {
        return new ClienteResumoDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}