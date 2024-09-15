package br.uff.ic;

import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Trecho;
import br.uff.ic.model.Voo;
import br.uff.ic.service.TrechoService;
import br.uff.ic.service.VooService;

import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class PrincipalVoo {

    private final VooService vooService = new VooService();
    private final TrechoService trechoService = new TrechoService();

    public void Principal() {

        double preco;
        int milhas;
        Voo voo;
        Trecho trecho;

        boolean continua = true;
        while (continua) {

            List<Trecho> trechos = new ArrayList<>();
            String origem, destino = null;

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println("1. Cadastrar um voo");
            System.out.println("2. Remover um voo");
            System.out.println("3. Listar todos os voos");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Vamos cadastrar os trechos do voo:");
                    boolean continua2 = true;
                    do {
                        if (destino == null) {
                            origem = Console.readLine("Digite a origem do voo: ");
                        } else {
                            origem = destino;
                        }
                        destino = Console.readLine("Digite o destino do trecho: ");
                        try {
                            trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, destino);
                        } catch (ObjetoNaoEncontradoException e) {
                            preco = Console.readDouble("Digite o preço do trecho: ");
                            milhas = Console.readInt("Digite as milhas do trecho: ");
                            trecho = new Trecho(origem, destino, preco, milhas);
                            trechoService.incluir(trecho);
                        }
                        trechos.add(trecho);
                        System.out.println('\n' + "Trecho adicionado ao voo com sucesso!");
                        String r = Console.readLine("Deseja continuar adicionando trechos? [s/n]");
                        if (r.equals("n")) {
                            continua2 = false;
                        }
                    } while (continua2);
                    voo = new Voo(trechos);
                    vooService.incluir(voo);
                    System.out.println('\n' + "Voo cadastrado com sucesso!");
                }
                case 2 -> {
                    int id = Console.readInt("Digite o ID do voo:");
                    try {
                        vooService.remover(id);
                        System.out.println('\n' + "Voo removido com sucesso!");
                    } catch (ObjetoNaoEncontradoException | ObjetoAssociadoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo v : voos) {
                        System.out.println(v);
                        System.out.println();
                    }
                }
                case 4 -> continua = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}