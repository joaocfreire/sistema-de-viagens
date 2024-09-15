package br.uff.ic.model;

import br.uff.ic.exception.DataHoraInvalidaException;
import br.uff.ic.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExecTrecho implements Serializable {
    @Id
    private int id;
    private ZonedDateTime dataHoraInicio;
    private ZonedDateTime dataHoraFim;
    private Trecho trecho;
    private ExecVoo execVoo;
    private List<Passagem> passagens;

    private static final NumberFormat NF;
    private static final DateTimeFormatter DTF;

    static {
        NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        NF.setMaximumFractionDigits (2);
        NF.setMinimumFractionDigits (2);
    }

    public ExecTrecho(Trecho trecho, String dataHoraInicio, String dataHoraFim) throws DataHoraInvalidaException {
        setDataHoraInicio(dataHoraInicio);
        setDataHoraFim(dataHoraFim);
        setExecVoo(execVoo);
        setTrecho(trecho);
        this.passagens = new ArrayList<>();
    }

    public String toString() {
        return  "ID: " + getId() + "\n" +
                "Origem: " + getTrecho().getOrigem() +
                " Destino: " + getTrecho().getDestino() + '\n' +
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

    public Trecho getTrecho() {
        return trecho;
    }

    public List<Passagem> getPassagens() {
        return passagens;
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

    public void setDataHoraFim(String dataHoraFim) throws DataHoraInvalidaException{
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
        if (this.dataHoraFim.isBefore(this.dataHoraInicio)) {
            throw new DataHoraInvalidaException("A dataHora de fim deve ser maior que a dataHora de início");
        }
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public void setExecVoo(ExecVoo execVoo) {
        this.execVoo = execVoo;
    }
}
