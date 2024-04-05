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
import com.dbank.api.modelo.cliente.ClienteSerivce;

import jakarta.validation.Valid;


@RestController
@RequestMapping("dbank/api/cliente")
@CrossOrigin
public class ClienteController {
    
    @Autowired
    private ClienteSerivce clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteRequest request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
}
