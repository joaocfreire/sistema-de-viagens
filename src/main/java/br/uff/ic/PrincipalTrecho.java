package br.uff.ic;

import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Trecho;
import br.uff.ic.service.TrechoService;
import corejava.Console;

import java.util.List;

public class PrincipalTrecho {

    private final TrechoService trechoService = new TrechoService();

    public void Principal() {
        String origem, destino;
        double preco;
        int milhas;
        Trecho trecho;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println("1. Cadastrar um trecho");
            System.out.println("2. Remover um trecho");
            System.out.println("3. Listar todos os trechos");
            System.out.println("4. Listar todos os trechos de um voo");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    origem = Console.readLine("Digite a origem do trecho: ");
                    destino = Console.readLine("Digite o destino do trecho: ");
                    try {
                        trechoService.recuperarTrechoPorOrigemDestino(origem, destino);
                        System.out.println("Trecho já cadastrado!");
                    } catch (ObjetoNaoEncontradoException e) {
                        preco = Console.readDouble("Digite o preço do trecho: ");
                        milhas = Console.readInt("Digite as milhas do trecho: ");
                        trecho = new Trecho(origem, destino, preco, milhas);
                        trechoService.incluir(trecho);
                        System.out.println('\n' + "Trecho cadastrado com sucesso!");
                    }
                }
                case 2 -> {
                    int id = Console.readInt("Digite o ID do trecho:");
                    try {
                        trechoService.remover(id);
                        System.out.println('\n' + "Trecho removido com sucesso!");
                    } catch (ObjetoNaoEncontradoException | ObjetoAssociadoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    List<Trecho> trechos = trechoService.recuperarTrechos();
                    for (Trecho t : trechos) {
                        System.out.println(t);
                        System.out.println();
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Digite o ID do voo:");
                    try {
                        List<Trecho> trechos = trechoService.recuperarTodosOsTrechosDeUmVoo(id);
                        System.out.println();
                        for (Trecho t : trechos) {
                            System.out.println(t);
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