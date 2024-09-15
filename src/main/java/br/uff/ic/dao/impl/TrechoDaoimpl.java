package br.uff.ic.dao.impl;

import br.uff.ic.dao.TrechoDAO;
import br.uff.ic.model.Trecho;

import java.util.List;

public class TrechoDaoimpl extends DAOGenericoImpl<Trecho> implements TrechoDAO {

    public List<Trecho> recuperarTrechoPorOrigem(String origem) {
        return map.values().stream().filter((trecho)-> trecho.getOrigem().equals(origem)).toList();
    }

    public Trecho recuperarTrechoPorOrigemDestino(String origem, String destino) {
        List<Trecho> candidatos = recuperarTrechoPorOrigem(origem);
        for (Trecho trecho: candidatos) {
            if (trecho.getDestino().equals(destino)) {
                return trecho;
            }
        }
        return null;
    }
}
