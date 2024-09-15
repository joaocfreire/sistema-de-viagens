package br.uff.ic.dao;

import br.uff.ic.model.Passagem;

import java.util.List;

public interface PassagemDAO extends DAOGenerico<Passagem> {

    List<Passagem> recuperarTodasAsPassagensDeUmaExecucaoDeTrecho(int id);

    List<Passagem> recuperarTodasAsPassagensDeUmCliente(int id);
}
