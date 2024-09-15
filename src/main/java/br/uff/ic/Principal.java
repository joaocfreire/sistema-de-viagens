package br.uff.ic;

import br.uff.ic.dao.*;
import br.uff.ic.model.*;
import br.uff.ic.util.FabricaDeDaos;

import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {

        PrincipalVoo principalVoo = new PrincipalVoo();
        PrincipalTrecho principalTrecho = new PrincipalTrecho();
        PrincipalExecVoo principalExecVoo = new PrincipalExecVoo();
        PrincipalExecTrecho principalExecTrecho = new PrincipalExecTrecho();
        PrincipalPassagem principalPassagem = new PrincipalPassagem();
        PrincipalCliente principalCliente = new PrincipalCliente();

        recuperarDados();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Voos");
            System.out.println("2. Tratar Trechos");
            System.out.println("3. Tratar Execuções de Voo");
            System.out.println("4. Tratar Execuções de Trecho");
            System.out.println("5. Tratar Passagens");
            System.out.println("6. Tratar Clientes");
            System.out.println("7. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");
            System.out.println();

            switch (opcao) {
                case 1 -> principalVoo.Principal();
                case 2 -> principalTrecho.Principal();
                case 3 -> principalExecVoo.Principal();
                case 4 -> principalExecTrecho.Principal();
                case 5 -> principalPassagem.Principal();
                case 6 -> principalCliente.Principal();
                case 7 -> {
                    continua = false;
                    salvarDados();
                    System.out.println("Até logo...");
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void recuperarDados() {
        try {
            ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
            PassagemDAO passDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
            VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
            TrechoDAO trechosDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
            ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
            ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

            FileInputStream fis = new FileInputStream("dados.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<Integer, Cliente> mapCliente = (Map<Integer, Cliente>) ois.readObject();
            clienteDAO.setMap(mapCliente);
            Integer cCliente = (Integer) ois.readObject();
            clienteDAO.setContador(cCliente);

            Map<Integer, Passagem> mapPassagem = (Map<Integer, Passagem>) ois.readObject();
            passDAO.setMap(mapPassagem);
            Integer cPassagem = (Integer) ois.readObject();
            passDAO.setContador(cPassagem);

            Map<Integer, Voo> mapVoo = (Map<Integer, Voo>) ois.readObject();
            vooDAO.setMap(mapVoo);
            Integer cVoo = (Integer) ois.readObject();
            vooDAO.setContador(cVoo);

            Map<Integer, Trecho> mapTrecho = (Map<Integer, Trecho>) ois.readObject();
            trechosDAO.setMap(mapTrecho);
            Integer cTrecho = (Integer) ois.readObject();
            trechosDAO.setContador(cTrecho);

            Map<Integer, ExecVoo> mapExecVoo = (Map<Integer, ExecVoo>) ois.readObject();
            execVooDAO.setMap(mapExecVoo);
            Integer cExecVoo = (Integer) ois.readObject();
            execVooDAO.setContador(cExecVoo);

            Map<Integer, ExecTrecho> mapExecTrecho = (Map<Integer, ExecTrecho>) ois.readObject();
            execTrechoDAO.setMap(mapExecTrecho);
            Integer cExecTrecho = (Integer) ois.readObject();
            execTrechoDAO.setContador(cExecTrecho);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo criado!");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void salvarDados() {
        ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
        PassagemDAO passDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
        VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
        TrechoDAO trechosDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
        ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
        ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

        Map<Integer, Cliente> mapCliente = clienteDAO.getMap();
        Map<Integer, Passagem> mapPassagem = passDAO.getMap();
        Map<Integer, Voo> mapVoo = vooDAO.getMap();
        Map<Integer, Trecho> mapTrecho = trechosDAO.getMap();
        Map<Integer, ExecVoo> mapExecVoo = execVooDAO.getMap();
        Map<Integer, ExecTrecho> mapExecTrecho = execTrechoDAO.getMap();

        Integer cCliente = clienteDAO.getContador();
        Integer cPassagem = passDAO.getContador();
        Integer cVoo = vooDAO.getContador();
        Integer cTrecho = trechosDAO.getContador();
        Integer cExecVoo = execVooDAO.getContador();
        Integer cExecTrecho = execTrechoDAO.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("dados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(mapCliente);
            oos.writeObject(cCliente);

            oos.writeObject(mapPassagem);
            oos.writeObject(cPassagem);

            oos.writeObject(mapVoo);
            oos.writeObject(cVoo);

            oos.writeObject(mapTrecho);
            oos.writeObject(cTrecho);

            oos.writeObject(mapExecVoo);
            oos.writeObject(cExecVoo);

            oos.writeObject(mapExecTrecho);
            oos.writeObject(cExecTrecho);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}