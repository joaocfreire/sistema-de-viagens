package br.uff.ic.model;

import br.uff.ic.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Voo implements Serializable {
    @Id
    private int id;
    private String origem;
    private String destino;
    private List<ExecVoo> execucoes;
    private List<Trecho> trechos;

    public Voo(List<Trecho> trechos) {
        this.origem = trechos.get(0).getOrigem();
        this.destino = trechos.get(trechos.size() -1).getDestino();
        this.execucoes = new ArrayList<>();
        this.trechos = trechos;
    }

    public String toString() {
        return "ID: " + getId() + "\n" +
                "Origem: " + getOrigem() +
                " Destino: " + getDestino() + "\n" +
                "Trechos: " + getTrechosMasc();
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

    public List<ExecVoo> getExecucoes() {
        return execucoes;
    }

    public List<Trecho> getTrechos() {
        return trechos;
    }

    public String getTrechosMasc() {
        StringBuilder resp = new StringBuilder(origem);
        for (Trecho t: trechos) {
            String str = " -> " + t.getDestino();
            resp.append(str);
        }
        return resp.toString();
    }
}
