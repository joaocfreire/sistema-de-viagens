package br.uff.ic.dao.impl;

import br.uff.ic.dao.ExecTrechoDAO;
import br.uff.ic.model.ExecTrecho;

import java.util.List;

public class ExecTrechoDaoImpl extends DAOGenericoImpl<ExecTrecho> implements ExecTrechoDAO {

    public List<ExecTrecho> recuperarTodasAsExecucoesDeUmTrecho(int id) {
        return map.values()
                .stream()
                .filter((execTrecho) -> execTrecho.getTrecho().getId() == id)
                .toList();
    }
}
