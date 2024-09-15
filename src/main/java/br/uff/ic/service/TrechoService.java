package br.uff.ic.service;

import br.uff.ic.dao.TrechoDAO;
import br.uff.ic.dao.VooDAO;
import br.uff.ic.util.FabricaDeDaos;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Trecho;
import br.uff.ic.model.Voo;

import java.util.List;

public class TrechoService {

    private final TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Trecho incluir(Trecho trecho) {
        return trechoDAO.incluir(trecho);
    }

    // Só permitir a remoção do trecho caso ele não esteja associado a nenhum outro objeto
    public Trecho remover(int id) {
        Trecho trecho = recuperarTrechoPorId(id);
        List<Voo> voos = vooDAO.recuperarVoosComTrecho(id);
        if (!voos.isEmpty()) {
            throw new ObjetoAssociadoException("Esse trecho está associado a um voo e não pode ser removido");
        }
        if (trecho.getExecucoes().isEmpty()) {
            trechoDAO.remover(trecho.getId());
        }
        else {
            throw new ObjetoAssociadoException("Esse trecho possui execuções cadastradas e não pode ser removido.");
        }
        return trecho;
    }

    // Verificar se o trecho existe
    public Trecho recuperarTrechoPorId(int id) {
        Trecho trecho = trechoDAO.recuperarPorId(id);
        if (trecho == null) {
            throw new ObjetoNaoEncontradoException("Trecho inexistente.");
        }
        return trecho;
    }

    public List<Trecho> recuperarTrechos() {
        return trechoDAO.recuperarTodos();
    }

    public List<Trecho> recuperarTodosOsTrechosDeUmVoo(int id) {
        Voo voo = vooDAO.recuperarPorId(id);
        if (voo == null) {
            throw new ObjetoNaoEncontradoException("Voo inexistente.");
        }
        return voo.getTrechos();
    }

    public Trecho recuperarTrechoPorOrigemDestino(String origem, String destino) {
        Trecho trecho = trechoDAO.recuperarTrechoPorOrigemDestino(origem, destino);
        if (trecho == null) {
            throw new ObjetoNaoEncontradoException("Trecho inexistente.");
        }
        return trecho;
    }
}
