import br.com.estudos.entities.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Banco inter = new Banco(77, "Inter", "Belo Horizonte");
        Banco caixa = new Banco(34, "Caixa Econômica", "Brasília");
        Banco santander = new Banco(92, "Santander", "São Paulo");
        Banco neon = new Banco(9973, "Neon", "São Paulo");
        Banco bradesco = new Banco(188, "Bradesco", "Rio de Janeiro");
        List<Banco> bancos = Arrays.asList(inter, santander, caixa, neon, bradesco);

        Cliente cliente = Cliente.builder()
                .nome("Gustavo")
                .dataNascimento(LocalDate.of(1997, 9, 15))
                .cpf("12345678909")
                .build();
        IConta primeiraConta = inter.criarConta(cliente, new Poupanca());
        IConta segundaConta = inter.criarConta(cliente, new Corrente());
        inter.listagemClientes();

        primeiraConta.depositar(100d);
        primeiraConta.depositar(150d);
        primeiraConta.depositar(2.5);
        primeiraConta.sacar(2.5);
        primeiraConta.extrato();

        segundaConta.depositar(100d);
        primeiraConta.transferir(segundaConta, 100d);
        segundaConta.extrato();

    }
}
