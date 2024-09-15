package br.uff.ic.dao;

import br.uff.ic.model.Trecho;

import java.util.List;

public interface TrechoDAO extends DAOGenerico<Trecho> {
    List<Trecho> recuperarTrechoPorOrigem(String origem);
    Trecho recuperarTrechoPorOrigemDestino(String origem, String destino);
}
