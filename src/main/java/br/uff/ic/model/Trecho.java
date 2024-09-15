package br.uff.ic.model;

import br.uff.ic.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Trecho implements Serializable {
    @Id
    private int id;
    private String origem;
    private String destino;
    private double preco;
    private int milhas;
    private List<ExecTrecho> execucoes;

    private static final NumberFormat NF;

    static {
        NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        NF.setMaximumFractionDigits(2);
        NF.setMinimumFractionDigits(2);
    }

    public Trecho(String origem, String destino, double preco, int milhas) {
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.milhas = milhas;
        this.execucoes = new ArrayList<>();
    }

    public String toString() {
        return "ID: " + getId() + " de: " + getOrigem() + ", para: " + getDestino() +
                ", por: " + getPrecoMasc() + ", milhas: " + getMilhas();
    }


    public Integer getId() {
        return id;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public double getPreco() {
        return preco;
    }

    public String getPrecoMasc() {
        return NF.format(preco);
    }

    public int getMilhas() {
        return milhas;
    }

    public List<ExecTrecho> getExecucoes() {
        return execucoes;
    }
}
