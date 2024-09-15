package br.uff.ic.dao.impl;

import br.uff.ic.dao.PassagemDAO;
import br.uff.ic.model.ExecTrecho;
import br.uff.ic.model.Passagem;

import java.util.ArrayList;
import java.util.List;

public class PassagemDaoImpl extends DAOGenericoImpl<Passagem> implements PassagemDAO {

    public List<Passagem> recuperarTodasAsPassagensDeUmaExecucaoDeTrecho(int id) {
        List<Passagem> resp = new ArrayList<>(List.of());

        List<Passagem> passagens = recuperarTodos();
        for (Passagem passagem: passagens) {
            List<ExecTrecho> execTrechos = passagem.getExecTrechos();
            for (ExecTrecho execTrecho: execTrechos) {
                if (execTrecho.getId() == id) {
                    resp.add(passagem);
                }
            }
        }

        return resp;
    }

    public List<Passagem> recuperarTodasAsPassagensDeUmCliente(int id) {
        return map.values()
                .stream()
                .filter((execTrecho) -> execTrecho.getCliente().getId() == id)
                .toList();
    }
}
