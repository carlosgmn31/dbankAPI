package com.dbank.api.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente) {

       return clienteRepository.save(cliente);
    }

    public Cliente pesquisaCliente(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if(cliente == null){
            throw  new IllegalArgumentException("Cliente não cadastrado");
        }
        return (cliente);
    }

    @Transactional
    public void alterarConta(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if(cliente == null){
            throw  new IllegalArgumentException("Cliente não cadastrado");
        }
        if (cliente.isAtiva() == true){
            cliente.setAtiva(false);
        }
        else
        cliente.setAtiva(true);

        clienteRepository.save(cliente);
    }

    @Transactional
    public void alterarLimiteDiario(String cpf,double limite) throws IllegalArgumentException{
        Cliente cliente = clienteRepository.findByCpf(cpf);
            if (cliente.isAtiva() == false){
                throw new IllegalArgumentException("não pode alterar o limte estando inativo");
            }
            if(cliente == null){
                throw  new IllegalArgumentException("Cliente não cadastrado");
            }
            if(limite < 100.00){
                throw new IllegalArgumentException("não pode deixar o limite abaixo de 100");
            }
            cliente.setLimte_diario(limite);
            clienteRepository.save(cliente);
    }
}