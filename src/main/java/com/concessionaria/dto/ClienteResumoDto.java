package com.concessionaria.dto;

public record ClienteResumoDto(
        Long id,
        String nome,
        String telefone,
        String email
) {
}
