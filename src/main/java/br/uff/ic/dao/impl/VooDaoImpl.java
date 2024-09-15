package br.uff.ic.dao.impl;

import br.uff.ic.dao.VooDAO;
import br.uff.ic.model.Trecho;
import br.uff.ic.model.Voo;

import java.util.LinkedList;
import java.util.List;

public class VooDaoImpl extends DAOGenericoImpl<Voo> implements VooDAO {

    public List<Voo> recuperarVoosComTrecho(Integer id) {
        LinkedList<Voo> resp = new LinkedList<>();
        for (Voo voo : map.values()) {
            for (Trecho trecho : voo.getTrechos()) {
                if (trecho.getId().equals(id)) {
                    resp.add(voo);
                    break;
                }
            }
        }
        return resp;
    }
}
