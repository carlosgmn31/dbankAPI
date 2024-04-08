package com.dbank.api.transferencia;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbank.api.modelo.transferencia.Transferencia;
import com.dbank.api.modelo.transferencia.TransferenciaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("dbank/api/cliente/transferencias")
@CrossOrigin
public class TransferenciaController {
    
    @Autowired
    private TransferenciaService transferenciaService;

    @Operation(
            summary = "Serviço responsável por retornar o extrato por periodo ao cliente, relatorio de transaçõe",
            description = "Este endpoint permite ao cliente obter um relatório das transações realizadas em sua conta dentro de um determinado período de tempo. " +
                    "Ao enviar uma requisição HTTP GET para este endpoint com as datas de início e fim do " +
                    "período desejado, o sistema processará a solicitação e retornará um extrato contendo as transações realizadas dentro desse intervalo de tempo."
    )
    @GetMapping("")
    public List<Transferencia> extratoPorPeriodo(@Valid @RequestBody TransferenciaExtratoDTO request ) {
        return transferenciaService.extrato(request.getCpf_origem(), request.getInicoData(), request.getFimData());

    }
    
}
