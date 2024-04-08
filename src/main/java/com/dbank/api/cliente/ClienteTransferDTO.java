package com.dbank.api.cliente;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteTransferDTO {

    @NotBlank(message = "tipoOperacao não pode ser nulo / em branco")
    private String tipoOperacao;

    @DecimalMin(value = "0.01" , message = "valor não pode ser inferior a 0,01")
    private double valor;
}   
