package br.uff.ic;

import br.uff.ic.exception.DataHoraInvalidaException;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.ExecTrecho;
import br.uff.ic.model.ExecVoo;
import br.uff.ic.model.Trecho;
import br.uff.ic.model.Voo;
import br.uff.ic.service.ExecTrechoService;
import br.uff.ic.service.ExecVooService;
import br.uff.ic.service.VooService;

import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class PrincipalExecVoo {

    private final ExecVooService execVooService = new ExecVooService();
    private final ExecTrechoService execTrechoService = new ExecTrechoService();
    private final VooService vooService = new VooService();

    public void Principal() {

        List<ExecVoo> execVoos;
        List<ExecTrecho> execTrechos;
        ExecVoo execVoo;
        ExecTrecho execTrecho;
        Voo voo;

        boolean continua = true;
        while (continua) {
            String dataHoraInicio, dataHoraFim = null;

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println("1. Cadastrar uma execução de voo");
            System.out.println("2. Remover uma execução de voo");
            System.out.println("3. Listar todas as execuções de voos");
            System.out.println("4. Listar todas as execuções de um voo");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    execTrechos = new ArrayList<>();
                    int id = Console.readInt("Digite o ID do voo: ");
                    try {
                        voo = vooService.recuperarVooPorId(id);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    System.out.println("Vamos cadastrar as execuções de trecho do voo:");
                    List<Trecho> trechos = voo.getTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println('\n' + "Para o trecho: ");
                        System.out.println(trecho);
                        if (dataHoraFim == null) {
                            dataHoraInicio = Console.readLine("Digite a data e hora de ínicio do voo: [dd/MM/aaaa hh:mm:ss]");
                        }
                        else {
                            dataHoraInicio = dataHoraFim;
                        }
                        dataHoraFim = Console.readLine("Digite a data e hora do fim do trecho: [dd/MM/aaaa hh:mm:ss]");
                        try {
                            execTrecho = new ExecTrecho(trecho, dataHoraInicio, dataHoraFim);
                            execTrechoService.incluir(execTrecho);
                            execTrechos.add(execTrecho);
                            System.out.println('\n' + "Execução de trecho cadastrada.");
                        } catch (IndexOutOfBoundsException | DataHoraInvalidaException e) {
                            System.out.println('\n' + e.getMessage());
                            break;
                        }
                    }
                    try {
                        execVoo = new ExecVoo(voo, execTrechos);
                        for (ExecTrecho et : execTrechos) {
                            et.setExecVoo(execVoo);
                        }
                        execVooService.incluir(execVoo);
                        System.out.println("Execução de voo cadastrada com sucesso!");

                    } catch (DataHoraInvalidaException | IndexOutOfBoundsException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    int id = Console.readInt("Informe o ID da execução de voo: ");
                    try {
                        execVooService.remover(id);
                        System.out.println('\n' + "Execução de voo removida com sucesso!");
                    }
                    catch (ObjetoNaoEncontradoException | ObjetoAssociadoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    execVoos = execVooService.recuperarTodasExecVoos();
                    for (ExecVoo ev : execVoos) {
                        System.out.println(ev);
                        System.out.println();
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Informe o ID do voo: ");
                    try {
                        vooService.recuperarVooPorId(id);
                        execVoos = execVooService.recuperarTodasAsExecucoesDeUmVoo(id);
                        for (ExecVoo ev : execVoos) {
                            System.out.println(ev);
                            System.out.println();
                        }
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 5 -> continua = false;
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
