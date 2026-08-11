package com.concessionaria.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(

        @NotBlank(message = "O nome é obrigatório" )
        String nome,


        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CPF é obrigatório")
        String cpf
) {

}
