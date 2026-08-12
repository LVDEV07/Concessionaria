package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Status;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarroRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O ano de fabricação é obrigatório")
        @Max(value = 2026, message = "deve ser menor ou igual a 2026")
        Integer anoFabricacao,

        @NotNull(message = "O ano do modelo é obrigatório")
        @Max(value = 2027, message = "deve ser menor ou igual a 2027")
        Integer anoModelo,

        @NotBlank(message = "O modelo é obrigatório")
        String modelo,

        @NotBlank(message = "A Marca é obrigatória")
        String marca,

        @NotBlank(message = "A cor é obrigatória")
        String cor,

        String placa,

        @NotBlank(message = "O chassi é obrigatório")
        String chassi,

        @NotNull(message = "A quilometragem é obrigatória")
        @PositiveOrZero(message = "não pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "deve ser maior que 0")
        BigDecimal preco,

        @NotNull(message = "O status é obrigatório")
        Status status,

        @NotNull(message = "A condição é obrigatória")
        Condicao condicao
) {
}