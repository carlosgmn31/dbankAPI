package com.dbank.api.modelo.cliente;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clientes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(unique = true)
    private String cpf;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column
    @Builder.Default
    private double saldo = 0.00;

    @Column
    @Builder.Default
    private String agencia = "0001";

    @Column
    private String conta;
    
    @Column
    @Builder.Default
    private boolean ativa = true;

    @Column
    @Builder.Default
    private double limte_diario = 2000.00;
}
