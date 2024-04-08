package com.dbank.api.modelo.transferencia;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbank.api.modelo.cliente.Cliente;
import com.dbank.api.modelo.cliente.ClienteRepository;
import com.dbank.api.transferencia.TransferenciaDTO;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaService {
    private final ClienteRepository clienteRepository;

    public TransferenciaService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Autowired
    private TransferenciaRepository transferenciaRepository;


    @Transactional
    public Transferencia save(Transferencia transferencia) {
       return transferenciaRepository.save(transferencia);
    }

     public Transferencia transfer(TransferenciaDTO transferenciaDTO) throws IllegalArgumentException {
        Cliente cliente = clienteRepository.findByCpf(transferenciaDTO.getCpf_origem());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não cadastrado");
        }
        System.out.println(transferenciaDTO.getTipo_transacao().toString());
        switch (transferenciaDTO.getTipo_transacao().toString()) {
            case "SAQUE":
                return saque(cliente, transferenciaDTO);
            case "DEPOSITO":
                return deposito(cliente, transferenciaDTO);
            default:
                throw new IllegalArgumentException("Tipo de operação inválido");
        }
}
    public Transferencia saque(Cliente cliente, TransferenciaDTO transferenciaDTO) throws IllegalArgumentException {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioDoDia = agora.withHour(0).withMinute(0);
        LocalDateTime fimDoDia = agora.withHour(23).withMinute(59);
        System.out.println(transferenciaRepository.sumByCpfOrigemAndDataTranferencia(cliente.getCpf(), inicioDoDia,fimDoDia) + transferenciaDTO.getValor());
        Boolean boolean1 = (transferenciaRepository.sumByCpfOrigemAndDataTranferencia(cliente.getCpf(), inicioDoDia,fimDoDia) + transferenciaDTO.getValor()) > 2000.0;
        System.out.println(boolean1);
        if (!cliente.isAtiva()) {
            throw new IllegalArgumentException("Sua conta está inativa!");
        }

        if (transferenciaDTO.getValor() < 0.01) {
            throw new IllegalArgumentException("Operações de saque têm valor mínimo de 0.01");
        }
        if (cliente.getSaldo() < transferenciaDTO.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente na conta");
        }
        if (transferenciaRepository.sumByCpfOrigemAndDataTranferencia(cliente.getCpf(), inicioDoDia,fimDoDia)==null && transferenciaDTO.getValor() > 2000.0 ){

            throw new IllegalArgumentException("Você atingiu o seu limite diario de saques, tente novamente amanhã!");
        }
        if ((transferenciaRepository.sumByCpfOrigemAndDataTranferencia(cliente.getCpf(), inicioDoDia,fimDoDia) + transferenciaDTO.getValor()) > 2000.0){
            throw new IllegalArgumentException("Você atingiu o seu limite diario de saques, tente novamente amanhã!");
        }
        cliente.setSaldo(cliente.getSaldo() - transferenciaDTO.getValor());
        
        Transferencia transferencia = save(transferenciaDTO.build());
        return transferencia;
    }
    
    public Transferencia deposito(Cliente cliente, TransferenciaDTO transferenciaDTO) throws IllegalArgumentException {
        if (!cliente.isAtiva()) {
            throw new IllegalArgumentException("Sua conta está inativa!");
        }
    
        if (transferenciaDTO.getValor() < 0.01) {
            throw new IllegalArgumentException("Operações de depósito têm valor mínimo de 0.01");
        }
        cliente.setSaldo(cliente.getSaldo() + transferenciaDTO.getValor());
        Transferencia transferencia = save(transferenciaDTO.build());
        return transferencia;
}

}
