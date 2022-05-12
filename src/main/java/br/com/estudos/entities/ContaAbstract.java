package br.com.estudos.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import services.GeraNumeroConta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class ContaAbstract implements IConta {
    protected String conta;
    protected String agencia;
    protected LocalDateTime dataCriacao;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected double saldo;
    private List<Operacao> operacoes = new ArrayList<>();

    public ContaAbstract() {
        this.dataCriacao = LocalDateTime.now();
        this.saldo = 0;
        this.conta = GeraNumeroConta.gerar();
        this.agencia = "0001";
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("O valor deve ser superior a R$ 0,00");
        saldo += valor;
        addOperacao(
                Operacao.builder()
                        .valor(valor)
                        .saldoAnterior(saldo - valor)
                        .tipo("DEPÓSITO")
                        .build()
        );
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("O valor deve ser superior a R$ 0,00");
        if ((saldo - valor) < 0d)
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        saldo -= valor;

        addOperacao(
                Operacao.builder()
                        .valor(valor)
                        .saldoAnterior(saldo + valor)
                        .tipo("SAQUE")
                        .build()
        );
    }

    @Override
    public void transferir(ContaAbstract contaDestino, double valor) {
        sacar(valor);
        contaDestino.depositar(valor);

        addOperacao(
                Operacao.builder()
                        .valor(valor)
                        .saldoAnterior(saldo + valor)
                        .tipo("TRANSFERÊNCIA")
                        .build()
        );
    }

    @Override
    public void extrato() {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        builder.append("\n\n------ Conta ------").append("\n");
        builder.append("Criação: ").append(dataCriacao.format(format)).append("\n");
        builder.append("Saldo Atual: ").append(saldo).append("\n");
        builder.append("\n--------------- Operações ---------------  ").append("\n");
        operacoes.forEach(operacao -> {
            builder.append("\n\n").append("-------------------------------------").append("\n");
            builder.append("--- Data: ").append(operacao.getData().format(format)).append("\n");
            builder.append("--- Tipo: ").append(operacao.getTipo()).append("\n");
            builder.append("--- Valor Operação: ").append(String.format("R$ %.2f", operacao.getValor())).append("\n");
            builder.append("--- Saldo Anterior: ").append(String.format("R$ %.2f", operacao.getSaldoAnterior())).append("\n");
            builder.append("--- Saldo Atual: ").append(String.format("R$ %.2f", operacao.getSaldoAtual())).append("\n");
            builder.append("-------------------------------------").append("\n");
        });
        System.out.println(builder);
    }

    private void addOperacao(Operacao operacao) {
        operacao.setSaldoAtual(saldo);
        operacoes.add(operacao);
    }
}
