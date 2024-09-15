package br.uff.ic.dao.impl;

import br.uff.ic.dao.ExecVooDAO;
import br.uff.ic.model.ExecVoo;

import java.util.List;

public class ExecVooDaoImpl extends DAOGenericoImpl<ExecVoo> implements ExecVooDAO {

    public List<ExecVoo> recuperarTodasAsExecucoesDeUmVoo(int id) {
        return map.values()
                .stream()
                .filter((execVoo) -> execVoo.getVoo().getId() == id)
                .toList();
    }
}
