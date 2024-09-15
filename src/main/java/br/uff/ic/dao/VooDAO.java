package br.uff.ic.dao;

import br.uff.ic.model.Voo;

import java.util.List;

public interface VooDAO extends DAOGenerico<Voo> {
    List<Voo> recuperarVoosComTrecho(Integer id);
}
