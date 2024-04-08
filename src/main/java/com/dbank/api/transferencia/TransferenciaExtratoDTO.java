package com.dbank.api.transferencia;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransferenciaExtratoDTO {
    
    @CPF
    private String cpf_origem;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicoData;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fimData;
}
