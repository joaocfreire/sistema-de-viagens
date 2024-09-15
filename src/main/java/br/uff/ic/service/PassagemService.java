package br.uff.ic.service;

import br.uff.ic.dao.PassagemDAO;
import br.uff.ic.util.FabricaDeDaos;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Passagem;

import java.util.List;

public class PassagemService {

    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);

    // Incluir na lista de passagens das execuções de trechos e do cliente
    public Passagem incluir(Passagem passagem) {
        for (int i = 0; i < passagem.getExecTrechos().size(); i++) {
            passagem.getExecTrechos().get(i).getPassagens().add(passagem);
        }
        passagem.getCliente().getPassagens().add(passagem);
        passagemDAO.incluir(passagem);
        return passagem;
    }

    // Remover da lista de passagens do cliente
    public Passagem remover(int id) {
        Passagem passagem = recuperarPorId(id);
        passagem.getCliente().getPassagens().remove(passagem);
        passagemDAO.remover(id);
        return passagem;
    }

    // Verificar se a passagem existe
    public Passagem recuperarPorId(int id) {
        Passagem passagem = passagemDAO.recuperarPorId(id);
        if (passagem == null) {
            throw new ObjetoNaoEncontradoException("Passagem inexistente.");
        }
        return passagem;
    }

    public List<Passagem> recuperarPassagens() {
        return passagemDAO.recuperarTodos();
    }

    public List<Passagem> recuperarTodasAsPassagensDeUmaExecucaoDeTrecho(int id) {
        return passagemDAO.recuperarTodasAsPassagensDeUmaExecucaoDeTrecho(id);
    }

    public List<Passagem> recuperarTodasAsPassagensDeUmCliente(int id) {
        return passagemDAO.recuperarTodasAsPassagensDeUmCliente(id);
    }
}
