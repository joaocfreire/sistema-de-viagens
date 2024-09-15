package br.uff.ic.model;

import br.uff.ic.exception.DataHoraInvalidaException;
import br.uff.ic.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ExecVoo implements Serializable {
    @Id
    private int id;
    private ZonedDateTime dataHoraInicio;
    private ZonedDateTime dataHoraFim;
    private Voo voo;
    private List<ExecTrecho> execTrechos;

    private static final NumberFormat NF;
    private static final DateTimeFormatter DTF;

    static {
        NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        NF.setMaximumFractionDigits (2);
        NF.setMinimumFractionDigits (2);
    }

    public ExecVoo(Voo voo, List<ExecTrecho> execTrechos) throws DataHoraInvalidaException {
        setDataHoraInicio(execTrechos.get(0).getDataHoraInicio());
        setDataHoraFim(execTrechos.get(execTrechos.size() - 1).getDataHoraFim());
        setVoo(voo);
        setExecTrechos(execTrechos);
    }

    public String toString() {
        return "ID da execução do voo: " + getId() + '\n' +
                "ID voo: " + getVoo().getId() + '\n' +
                "Origem: " + getVoo().getOrigem() +
                " Destino: " + getVoo().getDestino() + '\n' +
                "Inicio: " + getDataHoraInicio() +
                " Fim: " + getDataHoraFim();
    }


    public Integer getId() {
        return id;
    }

    public String getDataHoraInicio() {
        return DTF.format(dataHoraInicio.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public String getDataHoraFim() {
        return DTF.format(dataHoraFim.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public Voo getVoo() {
        return voo;
    }

    public List<ExecTrecho> getExecTrechos() {
        return execTrechos;
    }


    public void setDataHoraInicio(String dataHoraInicio) throws DataHoraInvalidaException {
        try { //  dd/mm/aaaa hh:mm:ss
            int dia = Integer.parseInt(dataHoraInicio.substring(0,2));
            int mes = Integer.parseInt(dataHoraInicio.substring(3,5));
            int ano = Integer.parseInt(dataHoraInicio.substring(6,10));

            int hora =    Integer.parseInt(dataHoraInicio.substring(11,13));
            int minuto =  Integer.parseInt(dataHoraInicio.substring(14,16));
            int segundo = Integer.parseInt(dataHoraInicio.substring(17,19));

            this.dataHoraInicio = ZonedDateTime.of(
                            ano, mes, dia, hora, minuto, segundo, 0,
                            ZoneId.of("America/Sao_Paulo"))
                    .withZoneSameInstant(ZoneId.of("UTC"));

        }
        catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e) {
            throw new DataHoraInvalidaException("Data e hora inválida.");
        }
    }

    public void setDataHoraFim(String dataHoraFim) throws DataHoraInvalidaException {
        try { //  dd/mm/aaaa hh:mm:ss
            int dia = Integer.parseInt(dataHoraFim.substring(0,2));
            int mes = Integer.parseInt(dataHoraFim.substring(3,5));
            int ano = Integer.parseInt(dataHoraFim.substring(6,10));

            int hora =    Integer.parseInt(dataHoraFim.substring(11,13));
            int minuto =  Integer.parseInt(dataHoraFim.substring(14,16));
            int segundo = Integer.parseInt(dataHoraFim.substring(17,19));

            this.dataHoraFim = ZonedDateTime.of(
                            ano, mes, dia, hora, minuto, segundo, 0,
                            ZoneId.of("America/Sao_Paulo"))
                    .withZoneSameInstant(ZoneId.of("UTC"));

        }
        catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e) {
            throw new DataHoraInvalidaException("Data e hora inválida.");
        }
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public void setExecTrechos(List<ExecTrecho> execTrechos) {
        this.execTrechos = execTrechos;
    }
}
