package br.com.estudos.entities;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Banco implements IServicosBanco {
    private int codigo;
    private String nome;
    private String cidade;
    private List<Cliente> clientes = new ArrayList<>();

    public Banco(int codigo, String nome, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
    }

    @Override
    public ContaAbstract criarConta(Cliente cliente, ContaAbstract conta) {
        if (!clientes.contains(cliente))
            clientes.add(cliente);
        cliente.adicionarConta(conta);
        return conta;
    }

    @Override
    public void listagemClientes() {
        StringBuilder builder = new StringBuilder();
        builder.append("Clientes Banco ").append(this.nome).append("\n");
        clientes.forEach(cliente -> {
            builder.append("\n").append(cliente.getNome());
        });
        System.out.println(builder);
    }

}
