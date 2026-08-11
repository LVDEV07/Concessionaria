package com.concessionaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Column(name = "Ano_de_Fabricacao", nullable = false)
    private Integer anoFabricacao;

    @Column(name = "Ano_do_Modelo", nullable = false)
    private Integer anoModelo;

    @Column(nullable = false)
    private String cor;

    @Column(unique = true)
    private String placa;

    @Column(unique = true, nullable = false)
    private String chassi;

    @Column(nullable = false)
    private Integer quilometragem;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Condicao condicao;






}
