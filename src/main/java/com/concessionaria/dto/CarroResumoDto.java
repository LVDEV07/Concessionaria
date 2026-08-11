package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Status;

import java.math.BigDecimal;

public record CarroResumoDto(Long id,
                             String nome,
                             String modelo,
                             String marca,
                             Integer anoFabricacao,
                             Integer anoModelo,
                             String cor,
                             String placa,
                             String chassi,
                             Integer quilometragem,
                             BigDecimal preco,
                             Status status,
                             Condicao condicao
) {
}
