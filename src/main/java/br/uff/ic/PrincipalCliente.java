package br.uff.ic;

import br.uff.ic.service.ClienteService;
import br.uff.ic.exception.ObjetoAssociadoException;
import br.uff.ic.exception.ObjetoJaCadastradoException;
import br.uff.ic.exception.ObjetoNaoEncontradoException;
import br.uff.ic.model.Cliente;

import corejava.Console;

import java.util.List;

public class PrincipalCliente {
    private final ClienteService clienteService = new ClienteService();

    public void Principal() {
        Cliente cliente;
        List<Cliente> clientes;
        String nome, cpf;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um cliente.");
            System.out.println("2. Alterar nome do cliente.");
            System.out.println("3. Remover um cliente.");
            System.out.println("4. Listar todos os clientes.");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Digite o nome do cliente: ");
                    cpf = Console.readLine("Digite o CPF do cliente: ");
                    cliente = new Cliente(nome, cpf);
                    try {
                        clienteService.incluir(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (ObjetoJaCadastradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2 -> {
                    int id = Console.readInt("Digite o ID do cliente: ");
                    try {
                        clienteService.recuperarPorId(id);
                        nome = Console.readLine("Digite o novo nome do cliente: ");
                        clienteService.alterar(id, nome);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    int id = Console.readInt("Digite o ID do cliente: ");
                    try {
                        clienteService.remover(id);
                        System.out.println("Cliente removido com sucesso!");
                    } catch (ObjetoNaoEncontradoException | ObjetoAssociadoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4 -> {
                    clientes = clienteService.recuperarClientes();
                    for(Cliente c : clientes) {
                        System.out.println(c);
                        System.out.println();
                    }
                }
                case 5 -> continua = false;
                default -> System.out.println("Opção invalida!");
            }
        }
    }
}
