package br.uff.ic.model;

import br.uff.ic.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Passagem implements Serializable {
    @Id
    private int id;
    private double preco;
    private List<ExecTrecho> execTrechos;
    private Cliente cliente;

    private static final NumberFormat NF;

    static {
        NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        NF.setMaximumFractionDigits(2);
        NF.setMinimumFractionDigits(2);
    }

    public Passagem(Cliente cliente, List<ExecTrecho> execTrechos) {
        setCliente(cliente);
        setExecTrechos(execTrechos);
        setPreco();
    }

    public String toString() {
        return "ID: " + getId() + '\n' +
                "Cliente: " + getCliente().getNome() + '\n' +
                "Preço: " + getPrecoMasc() + '\n' +
                "De: " + getExecTrechos().get(0).getTrecho().getOrigem() + " Para: " + getExecTrechos().get(getExecTrechos().size()-1).getTrecho().getDestino() +  '\n' +
                "Inicio: " + getExecTrechos().get(0).getDataHoraInicio() + " Fim: " + getExecTrechos().get(getExecTrechos().size()-1).getDataHoraFim();
    }

    public Integer getId() {
        return id;
    }

    public String getPrecoMasc() {
        return NF.format(preco);
    }

    public List<ExecTrecho> getExecTrechos() {
        return execTrechos;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public void setPreco() {
        double preco = 0;
        for (ExecTrecho execTrecho : execTrechos) {
            preco += execTrecho.getTrecho().getPreco();
        }
        this.preco = preco;
    }

    public void setExecTrechos(List<ExecTrecho> execTrechos) {
        this.execTrechos = execTrechos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
