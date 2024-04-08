package com.dbank.api.transferencia;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransferenciaExtratoDTO {
    
    @CPF
    private String cpf_origem;
    @Schema(example= "08/04/2023 14:55", description = "data e hora formato dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicoData;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fimData;
}
