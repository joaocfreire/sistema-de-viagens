package br.uff.ic;

import br.uff.ic.exception.DataHoraInvalidaException;
import br.uff.ic.model.*;
import br.uff.ic.service.ExecTrechoService;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.service.TrechoService;
import br.uff.ic.service.VooService;

import corejava.Console;

import java.util.List;

public class PrincipalExecTrecho {

    private final ExecTrechoService execTrechoService = new ExecTrechoService();
    private final TrechoService trechoService = new TrechoService();
    private final VooService vooService = new VooService();

    public void Principal() {
        ExecTrecho execTrecho;
        Trecho trecho;
        List<ExecTrecho> execTrechos;

        boolean continua = true;
        while (continua) {
            String dataHoraInicio, dataHoraFim;

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println("1. Cadastrar uma execução de trecho");
            System.out.println("2. Remover uma execução de trecho");
            System.out.println("3. Listar todas as execuções de trecho");
            System.out.println("4. Listar todas as execuções de um trecho");
            System.out.println("5. Relatório de execuções de trecho");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    int id = Console.readInt("Digite o ID do trecho: ");
                    try {
                        trecho = trechoService.recuperarTrechoPorId(id);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    dataHoraInicio = Console.readLine("Digite a data e hora de ínicio do trecho: [dd/MM/aaaa hh:mm:ss]");
                    dataHoraFim = Console.readLine("Digite a data e hora do fim do trecho: [dd/MM/aaaa hh:mm:ss]");
                    try {
                        execTrecho = new ExecTrecho(trecho, dataHoraInicio, dataHoraFim);
                        execTrechoService.incluir(execTrecho);
                        System.out.println('\n' + "Execução de trecho cadastrada com sucesso!");
                    } catch (IndexOutOfBoundsException | DataHoraInvalidaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    int id = Console.readInt("Informe o ID da execução de trecho: ");
                    try {
                        execTrechoService.remover(id);
                        System.out.println('\n' + "Execução de trecho removida com sucesso!");
                    }
                    catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    execTrechos = execTrechoService.recuperarTodasExecTrechos();
                    for (ExecTrecho exec : execTrechos) {
                        System.out.println(exec);
                        System.out.println();
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Informe o ID do trecho: ");
                    try {
                        trechoService.recuperarTrechoPorId(id);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    execTrechos = execTrechoService.recuperarTodasAsExecucoesDeUmTrecho(id);
                    for (ExecTrecho et : execTrechos) {
                        System.out.println(et);
                        System.out.println();
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Informe o ID de um voo: ");
                    try {
                        vooService.recuperarVooPorId(id);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 6 -> continua = false;
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
