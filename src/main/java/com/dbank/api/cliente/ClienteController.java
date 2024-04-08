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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("dbank/api/cliente")
@CrossOrigin
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransferenciaService transferenciaService;

    @Operation(
            summary = "Serviço responsável por salvar um cliente no sistema.",
            description = "Este endpoint é responsável por adicionar um novo cliente ao sistema. Ao enviar uma requisição HTTP POST para este endpoint com os dados do cliente, o sistema processará a solicitação e criará um novo registro de cliente no banco de dados."
    )
    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Serviço responsável por retornar innformações ao cliente, como saldo",
            description = "Este endpoint é responsável por fornecer informações ao cliente, incluindo o saldo disponível em sua conta. Ao enviar uma requisição HTTP GET para este endpoint, o sistema retornará o saldo atual disponível para o cliente."
    )
    @GetMapping("{cpf}/saldo")
    public Cliente getSaldoCliente(@PathVariable("cpf") String cpf) {

        return this.clienteService.pesquisaCliente(cpf);
    }

    @Operation(
            summary = "Serviço responsável por bloquear desbloquear conta para o cliente.",
            description = "Este endpoint permite ao cliente bloquear ou desbloquear sua conta no sistema. " +
                    "Ao enviar uma requisição HTTP PUT para este endpoint com o estado desejado da conta, o sistema processará a solicitação e atualizará o status da conta conforme especificado."
    )
    @PutMapping("{cpf}/alterarConta")
    public Cliente alterarConta(@PathVariable("cpf") String cpf) {
        clienteService.alterarConta(cpf);   

        return this.clienteService.pesquisaCliente(cpf);
    }
    @Operation(
            summary = "Serviço responsável por alterar o seu limite diario",
            description = "Este endpoint permite ao cliente alterar seu limite diário de transações no sistema. " +
                    "Ao enviar uma requisição HTTP PUT para este endpoint com o novo valor do limite diário desejado, o sistema processará a solicitação e atualizará o limite diário do cliente conforme especificado."
    )
    @PutMapping("{cpf}/alterarLimite/{limite}")
    public Cliente alterarLimites(@PathVariable("cpf") String cpf,@PathVariable("limite") double limite ){
        clienteService.alterarLimiteDiario(cpf,limite);

        return this.clienteService.pesquisaCliente(cpf);
    }
    @Operation(
            summary = "Serviço responsável por disparar a solicitação de transferencia",
            description = "Este endpoint permite ao cliente disparar uma solicitação de transferência de fundos para outra conta. Ao" +
                    " enviar uma requisição HTTP POST para este endpoint com os detalhes da transferência, o sistema processará a solicitação e iniciará o processo de transferência de fundos."
    )
    @PostMapping("/transferencias")
    public Transferencia transfer(@Valid @RequestBody TransferenciaDTO request) throws IllegalArgumentException {
          Transferencia transferencia = transferenciaService.transfer(request);
          return transferencia;
    }
}
