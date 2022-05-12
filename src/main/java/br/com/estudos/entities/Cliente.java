package br.com.estudos.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Cliente {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private List<ContaAbstract> contas = new ArrayList<>();

    public void adicionarConta(ContaAbstract conta) {
        contas.add(conta);
    }
}
