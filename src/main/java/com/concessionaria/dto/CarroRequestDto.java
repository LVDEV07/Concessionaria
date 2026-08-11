package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarroRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O ano de fabricação é obrigatório")
        Integer anoFabricacao,

        @NotNull(message = "O ano do modelo é obrigatório")
        Integer anoModelo,

        @NotBlank(message = "O modelo é obrigatório")
        String modelo,

        @NotBlank(message = "A Marca é obrigatória")
        String marca,

        @NotBlank(message = "A cor é obrigatória")
        String cor,

        @NotBlank(message = "A placa é obrigatória")
        String placa,

        @NotBlank(message = "O chassi é obrigatório")
        String chassi,

        @NotNull(message = "A quilometragem é obrigatória")
        Integer quilometragem,

        @NotNull(message = "O preço é obrigatório")
        BigDecimal preco,

        @NotNull(message = "O status é obrigatório")
        Status status,

        @NotNull(message = "A condição é obrigatória")
        Condicao condicao
) {
}
