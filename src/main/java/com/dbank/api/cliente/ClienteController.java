package com.dbank.api.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbank.api.modelo.cliente.Cliente;
import com.dbank.api.modelo.cliente.ClienteService;
import com.dbank.api.modelo.transferencia.Transferencia;
import com.dbank.api.modelo.transferencia.TransferenciaService;
import com.dbank.api.transferencia.TransferenciaDTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("dbank/api/cliente")
@CrossOrigin
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
    @GetMapping("{cpf}/saldo")
    public Cliente getSaldoCliente(@PathVariable("cpf") String cpf) {
        return this.clienteService.pesquisaCliente(cpf);
    }
    
    @PutMapping("{cpf}/alterarConta")
    public Cliente alterarConta(@PathVariable("cpf") String cpf) {
        clienteService.alterarConta(cpf);   

        return this.clienteService.pesquisaCliente(cpf);
    }

    @PutMapping("{cpf}/alterarLimite/{limite}")
    public Cliente alterarLimites(@PathVariable("cpf") String cpf,@PathVariable("limite") double limite ){
        clienteService.alterarLimiteDiario(cpf,limite);   

        return this.clienteService.pesquisaCliente(cpf);
    }

    @PostMapping("/transferencias")
    public Transferencia transfer(@Valid @RequestBody TransferenciaDTO request) throws IllegalArgumentException {
          Transferencia transferencia = transferenciaService.transfer(request);
          return transferencia;
    }
}
