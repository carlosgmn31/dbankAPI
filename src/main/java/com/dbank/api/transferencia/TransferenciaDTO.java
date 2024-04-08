package com.dbank.api.transferencia;


import org.hibernate.validator.constraints.br.CPF;

import com.dbank.api.modelo.transferencia.Transferencia;
import com.dbank.api.modelo.transferencia.Transferencia.TipoTransacao;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class TransferenciaDTO {
    
    @CPF
    private String cpf_origem;

    TipoTransacao tipo_transacao;

    @DecimalMin(value = "0.01" , message = "valor não pode ser inferior a 0,01")
    private double valor;
    
    public Transferencia build(){
        return Transferencia.builder()
        .cpfOrigem(cpf_origem)
        .tipoTransacao(tipo_transacao)
        .valor(valor)
        .build();
    }

}