package com.dbank.api.cliente;

import java.time.LocalDate;

import com.dbank.api.modelo.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class ClienteRequest {
    
    @NotBlank(message = "Nome não pode ser nulo / em branco")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotBlank(message = "cpf não pode ser nulo / em branco")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private String cpf;

    public Cliente build() {

       return Cliente.builder()
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .build();
    }

}
