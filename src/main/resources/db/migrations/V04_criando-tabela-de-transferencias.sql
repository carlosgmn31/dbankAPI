CREATE TABLE transferencias (
    id_transferencia VARCHAR(50) PRIMARY KEY,
    cpf_origem VARCHAR(14),
    valor DECIMAL(10, 2),
    tipo_transacao ENUM('DEPOSITO', 'SAQUE'),
    data_transferencia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cpf_origem) REFERENCES clientes(cpf),
    FOREIGN KEY (cpf_destino) REFERENCES clientes(cpf)
);