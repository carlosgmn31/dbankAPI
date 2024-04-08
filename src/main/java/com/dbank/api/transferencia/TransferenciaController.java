package com.dbank.api.transferencia;

import java.time.LocalDate;
import java.util.List;

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

    //extratos
    @GetMapping("")
    public List<Transferencia> extratoPorPeriodo(@Valid @RequestBody TransferenciaExtratoDTO request ) {
        return transferenciaService.extrato(request.getCpf_origem(), request.getInicoData(), request.getFimData());

    }
    
}
