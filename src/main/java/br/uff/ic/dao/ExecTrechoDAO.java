package br.uff.ic.dao;

import br.uff.ic.model.ExecTrecho;

import java.util.List;


public interface ExecTrechoDAO extends DAOGenerico<ExecTrecho> {

    List<ExecTrecho> recuperarTodasAsExecucoesDeUmTrecho(int id);
}
