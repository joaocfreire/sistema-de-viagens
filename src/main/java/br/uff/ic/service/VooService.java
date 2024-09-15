package br.uff.ic.service;

import br.uff.ic.dao.VooDAO;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.util.FabricaDeDaos;
import br.uff.ic.model.Voo;

import java.util.List;

public class VooService {

    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Voo incluir(Voo voo) {
        return vooDAO.incluir(voo);
    }

    // Só permitir a remoção do voo caso ele não esteja associado a nenhum outro objeto
    public Voo remover(int id) {
        Voo voo = recuperarVooPorId(id);
        if (voo.getExecucoes().isEmpty()) {
            vooDAO.remover(voo.getId());
        }
        else {
            throw new ObjetoAssociadoException("Esse voo possui execuções cadastradas e não pode ser removido.");
        }
        return voo;
    }

    // Verificar se o voo existe
    public Voo recuperarVooPorId(int id) {
        Voo voo = vooDAO.recuperarPorId(id);
        if (voo == null) {
            throw new ObjetoNaoEncontradoException("Voo inexistente");
        }
        return voo;
    }

    public List<Voo> recuperarVoos() {
        return vooDAO.recuperarTodos();
    }
}
