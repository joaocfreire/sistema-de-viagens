package br.uff.ic.service;

import br.uff.ic.dao.ClienteDAO;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoJaCadastradoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Cliente;
import br.uff.ic.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);

    // Só permitir a inclusão de um cliente que não tenha CPF repetido
    public Cliente incluir(Cliente cliente) {
        try {
            recuperarPorCPF(cliente.getCpf());
            throw new ObjetoJaCadastradoException("Já existe um cliente com esse CPF");
        } catch (ObjetoNaoEncontradoException e) {
            clienteDAO.incluir(cliente);
            return cliente;
        }
    }

    public void alterar(int id, String novoNome) {
        Cliente cliente = recuperarPorId(id);
        cliente.setNome(novoNome);
    }

    // Só permitir a remoção do cliente caso ele não esteja associada a nenhum outro objeto
    public Cliente remover(int id) {
        Cliente cliente = recuperarPorId(id);
        if (cliente.getPassagens().isEmpty()) {
            clienteDAO.remover(cliente.getId());
        }
        else {
            throw new ObjetoAssociadoException("Esse cliente comprou passagens e não pode ser removido.");
        }
        return cliente;
    }

    // Verificar se o cliente existe
    public Cliente recuperarPorId(int id) {
        Cliente cliente = clienteDAO.recuperarPorId(id);
        if (cliente == null) {
            throw new ObjetoNaoEncontradoException("Cliente inexistente");
        }
        return cliente;
    }

    // Verificar se o cliente existe
    public Cliente recuperarPorCPF(String cpf) {
        Cliente cliente = clienteDAO.recuperarPorCPF(cpf);
        if (cliente == null) {
            throw new ObjetoNaoEncontradoException("Cliente inexistente");
        }
        return cliente;
    }

    public List<Cliente> recuperarClientes() {
        return clienteDAO.recuperarTodos();
    }
}
