package com.dbank.api.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.dbank.api.modelo.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class ClienteDTO {
    
    @Size(min = 3,message = "nome deve ter ao menos 3 caracteres")
    private String nome;

    private LocalDate dataNascimento;
    
    @CPF(message = "CPF invalido!")
    @NotBlank(message = "Data de Nascimento não pode ser nulo / em branco")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private String cpf;

    @NotBlank(message = "Agencia não pode ser nulo / em branco")
    private String agencia;

    @NotBlank(message = "Conta não pode ser nulo / em branco")
    private String conta;
    
    public Cliente build() {

       return Cliente.builder()
           .nome(nome)
           .dataCriacao(LocalDateTime.now())
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .agencia(agencia)
           .conta(conta)
           .build();
    }

}
