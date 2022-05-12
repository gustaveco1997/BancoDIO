package br.com.estudos.entities;

public interface IServicosBanco {
    ContaAbstract criarConta(Cliente cliente, ContaAbstract conta);
    void listagemClientes();
}
