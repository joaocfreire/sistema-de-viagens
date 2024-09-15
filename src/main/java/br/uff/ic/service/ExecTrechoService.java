package br.uff.ic.service;

import br.uff.ic.dao.ExecTrechoDAO;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.ExecTrecho;
import br.uff.ic.model.Passagem;
import br.uff.ic.util.FabricaDeDaos;

import java.util.List;

public class ExecTrechoService {

    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);
    private final PassagemService passagemService = new PassagemService();

    // Incluir na lista de execuções de trecho do trecho correspondente
    public ExecTrecho incluir(ExecTrecho execTrecho) {
        execTrechoDAO.incluir(execTrecho);
        execTrecho.getTrecho().getExecucoes().add(execTrecho);
        return execTrecho;
    }

    // Remove a execução da lista de execuções do trecho e remove as passagen em cascata
    public ExecTrecho remover(int id) {
        ExecTrecho execTrecho = recuperarExecTrechoPorId(id);

        execTrecho.getTrecho().getExecucoes().remove(execTrecho);
        execTrechoDAO.remover(id);

        List<Passagem> passagens = execTrecho.getPassagens();
        for (Passagem passagem : passagens) {
            passagemService.remover(passagem.getId());
        }

        return execTrecho;
    }

    // Verificar se a execução de trecho existe
    public ExecTrecho recuperarExecTrechoPorId(int id) {
        ExecTrecho execTrecho = execTrechoDAO.recuperarPorId(id);
        if (execTrecho == null) {
            throw new ObjetoNaoEncontradoException("Execução de trecho inexistente");
        }
        return execTrecho;
    }

    public List<ExecTrecho> recuperarTodasExecTrechos() {
        return execTrechoDAO.recuperarTodos();
    }

    public List<ExecTrecho> recuperarTodasAsExecucoesDeUmTrecho(int id) {
        return execTrechoDAO.recuperarTodasAsExecucoesDeUmTrecho(id);
    }
}
