package com.dbank.api.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteSerivce {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente) {

       return clienteRepository.save(cliente);
    }
}
