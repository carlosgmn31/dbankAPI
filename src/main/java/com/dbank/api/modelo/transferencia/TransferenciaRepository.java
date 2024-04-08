package com.dbank.api.modelo.transferencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;



@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,String> {
    
    @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE cpfOrigem = ?1 and dataTranferencia BETWEEN ?2 AND ?3 ")
    Double sumByCpfOrigemAndDataTranferencia (String CpfOrigem,LocalDateTime inicioDoDia, LocalDateTime fimDoDia);

    @Query("FROM Transferencia t WHERE cpfOrigem = ?1 and dataTranferencia BETWEEN ?2 AND ?3 ")
    List<Transferencia> findByCpfOrigemAndDataTranferencia (String CpfOrigem,LocalDateTime inicioDoDia, LocalDateTime fimDoDia);
}
