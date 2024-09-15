package br.uff.ic.service;

import br.uff.ic.dao.ExecVooDAO;
import br.uff.ic.util.FabricaDeDaos;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.ExecVoo;

import java.util.List;

public class ExecVooService {

    private final ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);

    // Incluir na lista de execuções do voo
    public ExecVoo incluir(ExecVoo execVoo) {
        execVoo.getVoo().getExecucoes().add(execVoo);
        execVooDAO.incluir(execVoo);
        return execVoo;
    }

    // Só permitir a remoção do voo caso ele não esteja associado a nenhum outro objeto
    // Remover da lista de execuções do voo
    public ExecVoo remover(int id) {
        ExecVoo execVoo = recuperarExecVooPorId(id);
        if (execVoo.getExecTrechos().isEmpty()) {
            execVoo.getVoo().getExecucoes().remove(execVoo);
            execVooDAO.remover(execVoo.getId());
        }
        else {
            throw new ObjetoAssociadoException("Essa execução de voo possui execuções de trecho cadastradas e não pode ser removido.");
        }
        return execVoo;
    }

    // Verificar se a execução de voo existe
    public ExecVoo recuperarExecVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null) {
            throw new ObjetoNaoEncontradoException("Execução de voo inexistente");
        }
        return execVoo;
    }

    public List<ExecVoo> recuperarTodasExecVoos() {
        return execVooDAO.recuperarTodos();
    }

    public List<ExecVoo> recuperarTodasAsExecucoesDeUmVoo(int id) {
        return execVooDAO.recuperarTodasAsExecucoesDeUmVoo(id);
    }
}
