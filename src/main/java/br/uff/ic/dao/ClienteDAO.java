package br.uff.ic.dao;

import br.uff.ic.model.Cliente;

public interface ClienteDAO extends DAOGenerico<Cliente> {
    Cliente recuperarPorCPF(String cpf);
}
