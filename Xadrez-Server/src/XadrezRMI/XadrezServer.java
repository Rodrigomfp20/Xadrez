/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package XadrezRMI;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raulm
 */
public class XadrezServer extends UnicastRemoteObject implements InterfaceJogo {

    private ArrayList<Cliente> clientes;
    private static Mesa mesa;

    XadrezServer() throws RemoteException {
        super();
        clientes = new ArrayList();
        mesa = new Mesa();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = lerDadosInt("Introduza o porto no qual deseja abrir o servidor");
            Registry reg = LocateRegistry.createRegistry(port);
            System.out.println("IP do servidor RMI: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Porto do servidor RMI: " + port);
            XadrezServer serv = new XadrezServer();
            reg.rebind("Xadrez", serv);
            System.out.println("Servidor Iniciado");
            
            mesa.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // TODO code application logic here
    }

    public boolean registrarCliente(InterfaceCliente cliente) throws RemoteException {
        boolean player1 = false, player2 = false;
        for(int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getNomeUtilizador().equalsIgnoreCase(cliente.getNomeUtilizador())){
                return false;
            }
        }
        int tipo = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getTipo() == 1) {
                cliente.alteranome(1, clientes.get(i).getNomeUtilizador());
                player1 = true;
            } else if (clientes.get(i).getTipo() == 2) {
                cliente.alteranome(2, clientes.get(i).getNomeUtilizador());
                player2 = true;
            }
        }
        if (!player1) {
            tipo = 1;
            cliente.alteranome(1, cliente.getNomeUtilizador());
             for (int o = 0; o < clientes.size(); o++) {
                 clientes.get(o).getReferencia().alteranome(1, cliente.getNomeUtilizador());
                 System.out.println(clientes.get(o).getNomeUtilizador() + clientes.get(o).getTipo());
             }

        } else if (!player2) {
            tipo = 2;
            cliente.alteranome(2, cliente.getNomeUtilizador());
            for (int o = 0; o < clientes.size(); o++) {
                clientes.get(o).getReferencia().alteranome(2, cliente.getNomeUtilizador());
                System.out.println(clientes.get(o).getNomeUtilizador() + clientes.get(o).getTipo());
            }

        } else {
            tipo = 3;
            for (int o = 0; o < clientes.size(); o++) {
                if(clientes.get(o).getTipo() == 3){
                    cliente.adicionaObservador(clientes.get(o).getNomeUtilizador());
                }
                clientes.get(o).getReferencia().adicionaObservador(cliente.getNomeUtilizador());
                System.out.println(clientes.get(o).getNomeUtilizador() + clientes.get(o).getTipo());
            }
            cliente.adicionaObservador(cliente.getNomeUtilizador());
            

        }

        clientes.add(new Cliente(cliente, tipo, cliente.getNomeUtilizador()));
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("Nome: " + clientes.get(i).getNomeUtilizador() + " | Tipo: " + clientes.get(i).getTipo());
        }
        System.out.println("Cliente registrado: " + cliente);
        atualizarClientes();
        return true;
    }
    @Override
    public void moverPecaServidor(ArrayList<Peca> pecasTabuleiro,ArrayList<Peca> pecasForaBrancas,ArrayList<Peca> pecasForaPretas) throws RemoteException{
        mesa.setPiecesPosition(pecasTabuleiro);
                mesa.setPecasFora(pecasForaBrancas, pecasForaPretas);
        atualizarClientes();
    }


    private static String lerDados(String aMensagem) {
        System.out.println(aMensagem);
        Scanner teclado = new Scanner(System.in);
        String linha = teclado.nextLine();
        while (linha.isBlank()) {
            System.out.println("Devera introduzir algo.");
            System.out.println(aMensagem);
            linha = teclado.nextLine();
        }
        return linha;
    }

    private static int lerDadosInt(String aMensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerDados(aMensagem));
            } catch (NumberFormatException nfe) {
                System.out.println("Devera digitar um numero.");
            }
        }
    }

    @Override
    public void organizaPecas() throws RemoteException {
        mesa.organizaPecas();
        atualizarClientes();
    }
    
    public void atualizarClientes(){
        ArrayList<Peca> pecasTabuleiro = mesa.getPiecesPosition();
        ArrayList<Peca> pecasForaBrancas = mesa.getPecasBrancas();
        ArrayList<Peca> pecasForaPretas = mesa.getPecasPretas();
        for (int i = 0; i < clientes.size(); i++) {
            try {
                clientes.get(i).getReferencia().atualizarTabuleiro(pecasTabuleiro,pecasForaBrancas,pecasForaPretas);
            } catch (RemoteException ex) {
                Logger.getLogger(XadrezServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
