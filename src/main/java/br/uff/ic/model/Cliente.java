package br.uff.ic.model;

import br.uff.ic.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    @Id
    private int id;
    private String nome;
    private String cpf;
    private int milhas;
    private List<Passagem> passagens;

    public Cliente(String nome, String cpf) {
        setNome(nome);
        setCpf(cpf);
        this.milhas = 0;
        this.passagens = new ArrayList<>();
    }

    public String toString() {
        return "ID:" + getId() + '\n' +
                "Nome: " + getNome() + '\n' +
                "CPF: " + getCpf() + '\n' +
                "Milhas: " + getMilhas();
    }


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public int getMilhas() {
        this.milhas = 0;
        for (Passagem passagem : passagens) {
            List<ExecTrecho> execTrechos = passagem.getExecTrechos();
            for (ExecTrecho execTrecho : execTrechos) {
                this.milhas += execTrecho.getTrecho().getMilhas();
            }
        }
        return milhas;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
