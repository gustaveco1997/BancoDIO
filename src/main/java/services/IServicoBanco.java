package services;

import br.com.estudos.entities.Cliente;
import br.com.estudos.entities.ContaAbstract;

public interface IServicoBanco {
    ContaAbstract criarConta(Cliente cliente, ContaAbstract conta);
    void listagemClientes();
}
