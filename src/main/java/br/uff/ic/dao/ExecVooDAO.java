package br.uff.ic.dao;

import br.uff.ic.model.ExecVoo;

import java.util.List;

public interface ExecVooDAO extends DAOGenerico<ExecVoo> {
    List<ExecVoo> recuperarTodasAsExecucoesDeUmVoo(int id);
}
