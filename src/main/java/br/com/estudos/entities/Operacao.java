package br.com.estudos.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Operacao {
    @Builder.Default
    private LocalDateTime data = LocalDateTime.now();
    private String tipo;
    private double saldoAnterior;
    private double saldoAtual;
    private double valor;
    private String observacao;
}
