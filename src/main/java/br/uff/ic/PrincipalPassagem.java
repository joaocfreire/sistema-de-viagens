package br.uff.ic;

import br.uff.ic.service.ClienteService;
import br.uff.ic.service.ExecTrechoService;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.ExecTrecho;
import br.uff.ic.model.Passagem;
import br.uff.ic.model.Trecho;
import br.uff.ic.service.PassagemService;
import br.uff.ic.service.TrechoService;
import br.uff.ic.model.Cliente;

import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class PrincipalPassagem {

    private final PassagemService passagemService = new PassagemService();
    private final ClienteService clienteService = new ClienteService();
    private final ExecTrechoService execTrechoService = new ExecTrechoService();
    private final TrechoService trechoService = new TrechoService();

    public void Principal() {

        Cliente cliente;
        Passagem passagem;
        Trecho trecho;
        List<ExecTrecho> execTrechos;
        List<Passagem> passagens;
        String origem, destino, cpf;
        int op;

        boolean continua = true;
        while (continua) {
            execTrechos = new ArrayList<>();

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println("1. Cadastrar uma nova passagem");
            System.out.println("2. Remover uma passagem");
            System.out.println("3. Listar todas as passagens");
            System.out.println("4. Listar todas as passagens de um cliente");
            System.out.println("5. Listar todas as passagens de uma execução de trecho");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    cpf = Console.readLine("Digite o CPF do cliente: ");
                    try {
                        cliente = clienteService.recuperarPorCPF(cpf);
                        origem = Console.readLine("Digite a origem: ");
                        destino = Console.readLine("Digite o destino: ");
                        try {
                            trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, destino);
                            List<ExecTrecho> execs = execTrechoService.recuperarTodasAsExecucoesDeUmTrecho(trecho.getId());
                            System.out.println("Passagens para o trecho:");
                            int i = 0;
                            for(ExecTrecho exec: execs) {
                                i++;
                                System.out.println("\nOpção " + i + "\n");
                                System.out.println(exec);
                                System.out.println();
                            }
                            boolean continua2 = true;
                            do {
                                op = Console.readInt("Digite uma opção: ");
                                if (op < 1 || op > i) {
                                    System.out.println("Opção inválida!");
                                }
                                else {
                                    continua2 = false;
                                }
                            } while (continua2);

                            execTrechos.add(execs.get(op-1));

                            passagem = new Passagem(cliente, execTrechos);
                            passagemService.incluir(passagem);
                            System.out.println("Passagem cadastrada com sucesso!");
                            System.out.println(passagem);

                        } catch (ObjetoNaoEncontradoException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    }
                    catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    int id = Console.readInt("Digite o ID da passagem: ");
                    try {
                        passagemService.remover(id);
                        System.out.println("Passagem removida com sucesso!");
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    passagens = passagemService.recuperarPassagens();
                    for(Passagem p : passagens) {
                        System.out.println(p);
                        System.out.println();
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Digite o ID do cliente: ");
                    try {
                        passagens = passagemService.recuperarTodasAsPassagensDeUmCliente(id);
                        for(Passagem p : passagens) {
                            System.out.println(p);
                            System.out.println();
                        }
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Digite o ID da execução de trecho: ");
                    try {
                        passagens = passagemService.recuperarTodasAsPassagensDeUmaExecucaoDeTrecho(id);
                        for(Passagem p : passagens) {
                            System.out.println(p);
                            System.out.println();
                        }
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> continua = false;
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
