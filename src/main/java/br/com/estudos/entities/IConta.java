package br.com.estudos.entities;

public interface IConta {
    void depositar(double valor);
    void sacar(double valor);
    void transferir(ContaAbstract contaDestino, double valor);
    void extrato();
    //void pagarConta();
}
