package com.dbank.api.modelo.transferencia;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transferencias")
public class Transferencia {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_transferencia;

    @Column(name = "cpf_origem")
    private String cpfOrigem;

    @Column
    private double valor;

    @Column(name = "data_transferencia")
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataTranferencia;

    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao")
    private TipoTransacao tipoTransacao;

    public enum TipoTransacao {
        DEPOSITO, SAQUE
    }
}
