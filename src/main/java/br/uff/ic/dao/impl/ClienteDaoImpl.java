package br.uff.ic.dao.impl;

import br.uff.ic.dao.ClienteDAO;
import br.uff.ic.model.Cliente;

public class ClienteDaoImpl extends DAOGenericoImpl<Cliente> implements ClienteDAO {

    public Cliente recuperarPorCPF(String cpf) {
        try {
            return map.values().stream().filter((cliente) -> cliente.getCpf().equals(cpf)).toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
